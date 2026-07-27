package androidx.constraintlayout.core.motion.utils;

/* loaded from: classes.dex */
public class LinearCurveFit extends CurveFit {
    private static final String TAG = "LinearCurveFit";
    private boolean mExtrapolate = true;
    double[] mSlopeTemp;
    private double[] mT;
    private double mTotalLength;
    private double[][] mY;

    public LinearCurveFit(double[] time, double[][] y) {
        int dim;
        double px;
        this.mTotalLength = Double.NaN;
        char c = 0;
        int dim2 = y[0].length;
        this.mSlopeTemp = new double[dim2];
        this.mT = time;
        this.mY = y;
        if (dim2 > 2) {
            double sum = com.github.mikephil.charting.utils.Utils.DOUBLE_EPSILON;
            double lastx = com.github.mikephil.charting.utils.Utils.DOUBLE_EPSILON;
            double lasty = com.github.mikephil.charting.utils.Utils.DOUBLE_EPSILON;
            int i = 0;
            while (i < time.length) {
                double px2 = y[i][c];
                double py = y[i][c];
                if (i <= 0) {
                    dim = dim2;
                    px = px2;
                } else {
                    dim = dim2;
                    px = px2;
                    sum += Math.hypot(px2 - lastx, py - lasty);
                }
                lastx = px;
                lasty = py;
                i++;
                dim2 = dim;
                c = 0;
            }
            this.mTotalLength = com.github.mikephil.charting.utils.Utils.DOUBLE_EPSILON;
        }
    }

    private double getLength2D(double t) {
        LinearCurveFit linearCurveFit = this;
        if (Double.isNaN(linearCurveFit.mTotalLength)) {
            return com.github.mikephil.charting.utils.Utils.DOUBLE_EPSILON;
        }
        int n = linearCurveFit.mT.length;
        if (t <= linearCurveFit.mT[0]) {
            return com.github.mikephil.charting.utils.Utils.DOUBLE_EPSILON;
        }
        if (t >= linearCurveFit.mT[n - 1]) {
            return linearCurveFit.mTotalLength;
        }
        double sum = com.github.mikephil.charting.utils.Utils.DOUBLE_EPSILON;
        double last_x = com.github.mikephil.charting.utils.Utils.DOUBLE_EPSILON;
        double last_y = com.github.mikephil.charting.utils.Utils.DOUBLE_EPSILON;
        int i = 0;
        while (i < n - 1) {
            double px = linearCurveFit.mY[i][0];
            double py = linearCurveFit.mY[i][1];
            if (i > 0) {
                double d = px - last_x;
                double last_x2 = py - last_y;
                sum += Math.hypot(d, last_x2);
            }
            last_x = px;
            last_y = py;
            if (t == linearCurveFit.mT[i]) {
                return sum;
            }
            if (t >= linearCurveFit.mT[i + 1]) {
                i++;
                linearCurveFit = this;
            } else {
                double h = linearCurveFit.mT[i + 1] - linearCurveFit.mT[i];
                double x = (t - linearCurveFit.mT[i]) / h;
                double x1 = linearCurveFit.mY[i][0];
                double x2 = linearCurveFit.mY[i + 1][0];
                double y1 = linearCurveFit.mY[i][1];
                double y2 = linearCurveFit.mY[i + 1][1];
                return sum + Math.hypot(py - (((1.0d - x) * y1) + (y2 * x)), px - (((1.0d - x) * x1) + (x2 * x)));
            }
        }
        return com.github.mikephil.charting.utils.Utils.DOUBLE_EPSILON;
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getPos(double t, double[] v) {
        int n = this.mT.length;
        int dim = this.mY[0].length;
        if (this.mExtrapolate) {
            if (t <= this.mT[0]) {
                getSlope(this.mT[0], this.mSlopeTemp);
                for (int j = 0; j < dim; j++) {
                    v[j] = this.mY[0][j] + ((t - this.mT[0]) * this.mSlopeTemp[j]);
                }
                return;
            }
            if (t >= this.mT[n - 1]) {
                getSlope(this.mT[n - 1], this.mSlopeTemp);
                for (int j2 = 0; j2 < dim; j2++) {
                    v[j2] = this.mY[n - 1][j2] + ((t - this.mT[n - 1]) * this.mSlopeTemp[j2]);
                }
                return;
            }
        } else {
            if (t <= this.mT[0]) {
                for (int j3 = 0; j3 < dim; j3++) {
                    v[j3] = this.mY[0][j3];
                }
                return;
            }
            if (t >= this.mT[n - 1]) {
                for (int j4 = 0; j4 < dim; j4++) {
                    v[j4] = this.mY[n - 1][j4];
                }
                return;
            }
        }
        for (int i = 0; i < n - 1; i++) {
            if (t == this.mT[i]) {
                for (int j5 = 0; j5 < dim; j5++) {
                    v[j5] = this.mY[i][j5];
                }
            }
            if (t < this.mT[i + 1]) {
                double h = this.mT[i + 1] - this.mT[i];
                double x = (t - this.mT[i]) / h;
                for (int j6 = 0; j6 < dim; j6++) {
                    double y1 = this.mY[i][j6];
                    double y2 = this.mY[i + 1][j6];
                    v[j6] = ((1.0d - x) * y1) + (y2 * x);
                }
                return;
            }
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getPos(double t, float[] v) {
        int n = this.mT.length;
        int dim = this.mY[0].length;
        if (this.mExtrapolate) {
            if (t <= this.mT[0]) {
                getSlope(this.mT[0], this.mSlopeTemp);
                for (int j = 0; j < dim; j++) {
                    v[j] = (float) (this.mY[0][j] + ((t - this.mT[0]) * this.mSlopeTemp[j]));
                }
                return;
            }
            if (t >= this.mT[n - 1]) {
                getSlope(this.mT[n - 1], this.mSlopeTemp);
                for (int j2 = 0; j2 < dim; j2++) {
                    v[j2] = (float) (this.mY[n - 1][j2] + ((t - this.mT[n - 1]) * this.mSlopeTemp[j2]));
                }
                return;
            }
        } else {
            if (t <= this.mT[0]) {
                for (int j3 = 0; j3 < dim; j3++) {
                    v[j3] = (float) this.mY[0][j3];
                }
                return;
            }
            if (t >= this.mT[n - 1]) {
                for (int j4 = 0; j4 < dim; j4++) {
                    v[j4] = (float) this.mY[n - 1][j4];
                }
                return;
            }
        }
        for (int i = 0; i < n - 1; i++) {
            if (t == this.mT[i]) {
                for (int j5 = 0; j5 < dim; j5++) {
                    v[j5] = (float) this.mY[i][j5];
                }
            }
            if (t < this.mT[i + 1]) {
                double h = this.mT[i + 1] - this.mT[i];
                double x = (t - this.mT[i]) / h;
                for (int j6 = 0; j6 < dim; j6++) {
                    double y1 = this.mY[i][j6];
                    double y2 = this.mY[i + 1][j6];
                    v[j6] = (float) (((1.0d - x) * y1) + (y2 * x));
                }
                return;
            }
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double getPos(double t, int j) {
        int n = this.mT.length;
        if (this.mExtrapolate) {
            if (t <= this.mT[0]) {
                return this.mY[0][j] + ((t - this.mT[0]) * getSlope(this.mT[0], j));
            }
            if (t >= this.mT[n - 1]) {
                return this.mY[n - 1][j] + ((t - this.mT[n - 1]) * getSlope(this.mT[n - 1], j));
            }
        } else {
            if (t <= this.mT[0]) {
                return this.mY[0][j];
            }
            if (t >= this.mT[n - 1]) {
                return this.mY[n - 1][j];
            }
        }
        for (int i = 0; i < n - 1; i++) {
            if (t == this.mT[i]) {
                return this.mY[i][j];
            }
            if (t < this.mT[i + 1]) {
                double h = this.mT[i + 1] - this.mT[i];
                double x = (t - this.mT[i]) / h;
                double y1 = this.mY[i][j];
                double y2 = this.mY[i + 1][j];
                return ((1.0d - x) * y1) + (y2 * x);
            }
        }
        return com.github.mikephil.charting.utils.Utils.DOUBLE_EPSILON;
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getSlope(double t, double[] v) {
        double t2;
        int n = this.mT.length;
        int dim = this.mY[0].length;
        if (t <= this.mT[0]) {
            t2 = this.mT[0];
        } else if (t < this.mT[n - 1]) {
            t2 = t;
        } else {
            t2 = this.mT[n - 1];
        }
        for (int i = 0; i < n - 1; i++) {
            if (t2 <= this.mT[i + 1]) {
                double h = this.mT[i + 1] - this.mT[i];
                for (int j = 0; j < dim; j++) {
                    double y1 = this.mY[i][j];
                    double y2 = this.mY[i + 1][j];
                    v[j] = (y2 - y1) / h;
                }
                return;
            }
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double getSlope(double t, int j) {
        int n = this.mT.length;
        if (t < this.mT[0]) {
            t = this.mT[0];
        } else if (t >= this.mT[n - 1]) {
            t = this.mT[n - 1];
        }
        for (int i = 0; i < n - 1; i++) {
            if (t <= this.mT[i + 1]) {
                double h = this.mT[i + 1] - this.mT[i];
                double y1 = this.mY[i][j];
                double y2 = this.mY[i + 1][j];
                return (y2 - y1) / h;
            }
        }
        return com.github.mikephil.charting.utils.Utils.DOUBLE_EPSILON;
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double[] getTimePoints() {
        return this.mT;
    }
}
