package androidx.work;

/* loaded from: classes.dex */
public abstract class InputMergerFactory {
    public abstract InputMerger createInputMerger(String className);

    public final InputMerger createInputMergerWithDefaultFallback(String className) {
        InputMerger inputMerger = createInputMerger(className);
        if (inputMerger == null) {
            return InputMerger.fromClassName(className);
        }
        return inputMerger;
    }

    public static InputMergerFactory getDefaultInputMergerFactory() {
        return new InputMergerFactory() { // from class: androidx.work.InputMergerFactory.1
            @Override // androidx.work.InputMergerFactory
            public InputMerger createInputMerger(String className) {
                return null;
            }
        };
    }
}
