import nodemailer from "nodemailer";

const transporter = nodemailer.createTransport({
  host: process.env.SMTP_HOST ?? "smtp.gmail.com",
  port: Number(process.env.SMTP_PORT ?? 587),
  secure: false,
  auth: {
    user: process.env.SMTP_USER,
    pass: process.env.SMTP_PASS,
  },
});

export async function sendOtpEmail(to: string, name: string, otp: string) {
  const from = process.env.SMTP_FROM ?? process.env.SMTP_USER ?? "noreply@bookmark.pk";

  await transporter.sendMail({
    from: `"Bookmark SFA" <${from}>`,
    to,
    subject: "Your Password Reset OTP",
    html: `
      <div style="font-family:sans-serif;max-width:480px;margin:0 auto;padding:24px;border:1px solid #eee;border-radius:12px;">
        <h2 style="color:#C8102E;margin-bottom:4px;">Bookmark SFA</h2>
        <p style="color:#555;">Hi <strong>${name}</strong>,</p>
        <p style="color:#555;">Use the OTP below to reset your password. It expires in <strong>15 minutes</strong>.</p>
        <div style="font-size:36px;font-weight:900;letter-spacing:10px;text-align:center;color:#C8102E;padding:20px 0;">
          ${otp}
        </div>
        <p style="color:#999;font-size:12px;">If you did not request this, ignore this email.</p>
      </div>
    `,
  });
}
