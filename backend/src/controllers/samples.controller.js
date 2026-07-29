import prisma from '../config/database.js';
import { AppError } from '../middleware/errorHandler.js';

// GET /api/v1/samples/my — get current user's sample budget + requests
export const getMySamples = async (req, res, next) => {
  try {
    const bookerId = req.user.id;

    const booker = await prisma.booker.findUnique({
      where: { id: bookerId },
      select: { annualSampleLimit: true, sampleUsed: true },
    });

    const requests = await prisma.sampleRequest.findMany({
      where: { bookerId },
      include: { items: { include: { product: { select: { id: true, name: true, retailPrice: true } } } } },
      orderBy: { createdAt: 'desc' },
      take: 20,
    });

    const annualLimit = Number(booker?.annualSampleLimit ?? 0);
    const used = Number(booker?.sampleUsed ?? 0);

    res.json({
      success: true,
      data: {
        annualLimit,
        used,
        remaining: Math.max(0, annualLimit - used),
        requests: requests.map(r => ({
          id: r.id,
          status: r.status,
          totalValuePkr: Number(r.totalValuePkr),
          reminder10Sent: r.reminder10Sent,
          reminder20Sent: r.reminder20Sent,
          dispatchedAt: r.dispatchedAt,
          recoveredAt: r.recoveredAt,
          adminNote: r.adminNote,
          createdAt: r.createdAt,
          items: r.items.map(i => ({
            productId: i.productId,
            productName: i.product.name,
            quantity: i.quantity,
            unitPrice: Number(i.unitPrice),
            subtotal: Number(i.subtotal),
          })),
        })),
      },
    });
  } catch (err) { next(err); }
};

// POST /api/v1/samples/request — submit a sample request
export const requestSamples = async (req, res, next) => {
  try {
    const bookerId = req.user.id;
    const { items } = req.body; // [{ productId, quantity }]

    if (!Array.isArray(items) || items.length === 0) {
      throw new AppError('INVALID_ITEMS', 400, 'At least one item is required');
    }

    const booker = await prisma.booker.findUnique({
      where: { id: bookerId },
      select: { annualSampleLimit: true, sampleUsed: true },
    });

    const remaining = Number(booker.annualSampleLimit) - Number(booker.sampleUsed);

    // Get product prices
    const products = await prisma.product.findMany({
      where: { id: { in: items.map(i => i.productId) } },
      select: { id: true, retailPrice: true },
    });

    const priceMap = Object.fromEntries(products.map(p => [p.id, Number(p.retailPrice)]));

    let totalValue = 0;
    const sampleItems = items.map(item => {
      const unitPrice = priceMap[item.productId] ?? 0;
      const subtotal = unitPrice * item.quantity;
      totalValue += subtotal;
      return { productId: item.productId, quantity: item.quantity, unitPrice, subtotal };
    });

    if (totalValue > remaining) {
      throw new AppError(
        'EXCEEDS_LIMIT',
        422,
        `Request value PKR ${totalValue.toFixed(0)} exceeds your remaining annual limit of PKR ${remaining.toFixed(0)}`
      );
    }

    const request = await prisma.$transaction(async (tx) => {
      const req = await tx.sampleRequest.create({
        data: {
          bookerId,
          totalValuePkr: totalValue,
          status: 'pending',
          items: { create: sampleItems },
        },
        include: { items: true },
      });
      // Reserve the budget
      await tx.booker.update({
        where: { id: bookerId },
        data: { sampleUsed: { increment: totalValue } },
      });
      return req;
    });

    res.status(201).json({ success: true, data: request });
  } catch (err) { next(err); }
};

// POST /api/v1/samples/:id/recover — mark a sample request as recovered
export const markRecovered = async (req, res, next) => {
  try {
    const id = parseInt(req.params.id);
    const bookerId = req.user.id;

    const sample = await prisma.sampleRequest.findFirst({ where: { id, bookerId } });
    if (!sample) throw new AppError('NOT_FOUND', 404, 'Sample request not found');
    if (sample.recoveredAt) throw new AppError('ALREADY_RECOVERED', 409, 'Already marked as recovered');

    const updated = await prisma.$transaction(async (tx) => {
      const s = await tx.sampleRequest.update({
        where: { id },
        data: { status: 'recovered', recoveredAt: new Date() },
      });
      // Release the budget reservation
      await tx.booker.update({
        where: { id: bookerId },
        data: { sampleUsed: { decrement: Number(sample.totalValuePkr) } },
      });
      return s;
    });

    res.json({ success: true, data: updated, message: 'Sample books marked as recovered' });
  } catch (err) { next(err); }
};

// ADMIN: GET /api/v1/samples/admin — get all sample requests
export const adminGetSamples = async (req, res, next) => {
  try {
    const { status } = req.query;
    const samples = await prisma.sampleRequest.findMany({
      where: status ? { status } : {},
      include: {
        items: { include: { product: { select: { id: true, name: true } } } },
      },
      orderBy: { createdAt: 'desc' },
      take: 100,
    });
    res.json({ success: true, data: samples });
  } catch (err) { next(err); }
};

// ADMIN: PATCH /api/v1/samples/:id/approve — approve or reject sample request
export const adminApproveSample = async (req, res, next) => {
  try {
    const id = parseInt(req.params.id);
    const { status, adminNote } = req.body; // status: 'dispatched' | 'rejected'

    if (!['dispatched', 'rejected'].includes(status)) {
      throw new AppError('INVALID_STATUS', 400, 'Status must be dispatched or rejected');
    }

    const sample = await prisma.sampleRequest.findUnique({ where: { id } });
    if (!sample) throw new AppError('NOT_FOUND', 404, 'Sample not found');

    const updated = await prisma.$transaction(async (tx) => {
      const s = await tx.sampleRequest.update({
        where: { id },
        data: {
          status,
          adminNote: adminNote ?? null,
          ...(status === 'dispatched' && { dispatchedAt: new Date() }),
        },
      });
      // If rejected, release budget
      if (status === 'rejected') {
        await tx.booker.update({
          where: { id: sample.bookerId },
          data: { sampleUsed: { decrement: Number(sample.totalValuePkr) } },
        });
      }
      return s;
    });

    res.json({ success: true, data: updated });
  } catch (err) { next(err); }
};
