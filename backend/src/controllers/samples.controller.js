import prisma from '../config/database.js';
import { AppError } from '../middleware/errorHandler.js';

export const requestSample = async (req, res, next) => {
  try {
    const { productId, quantity, visitId } = req.body;
    const userId = req.user.id;

    const [product, user] = await Promise.all([
      prisma.product.findUnique({ where: { id: productId } }),
      prisma.user.findUnique({ where: { id: userId } }),
    ]);

    if (!product) throw new AppError('NOT_FOUND', 404, 'Product not found');

    const totalValue = product.pricePkr.toNumber() * quantity;
    const remainingLimit = user.annualSampleLimitPkr.toNumber() - user.sampleUsedPkr.toNumber();

    if (totalValue > remainingLimit) {
      throw new AppError('SAMPLE_LIMIT_EXCEEDED', 422, `Request of PKR ${totalValue} exceeds your remaining annual budget of PKR ${remainingLimit}`);
    }

    const request = await prisma.$transaction(async (tx) => {
      const req_ = await tx.sampleRequest.create({
        data: { userId, productId, visitId, quantity, totalValuePkr: totalValue },
      });
      await tx.user.update({ where: { id: userId }, data: { sampleUsedPkr: { increment: totalValue } } });
      return req_;
    });

    res.status(201).json({
      success: true,
      data: { id: request.id, totalValuePkr: totalValue.toFixed(2), remainingLimitPkr: (remainingLimit - totalValue).toFixed(2) },
    });
  } catch (err) { next(err); }
};

export const recoverSample = async (req, res, next) => {
  try {
    const id = parseInt(req.params.id);
    const sample = await prisma.sampleRequest.findFirst({ where: { id, userId: req.user.id } });
    if (!sample) throw new AppError('NOT_FOUND', 404, 'Sample request not found');

    await prisma.sampleRequest.update({ where: { id }, data: { status: 'recovered', recoveredAt: new Date() } });
    res.json({ success: true, data: { status: 'recovered', recoveredAt: new Date() } });
  } catch (err) { next(err); }
};
