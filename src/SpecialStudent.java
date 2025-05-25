public class SpecialStudent extends Student {
    private final static int MAX_CLASSES = 2;

    public SpecialStudent(String name, String id) {
        super(name, id);
    }

    @Override
    public void signClass(Class class1) {
        if (this.classes.size() >= MAX_CLASSES) {
            throw new IllegalStateException("Alunos especiais não podem cursar mais de:" + MAX_CLASSES + "materias!");
        }
        super.signClass(class1);
    }
}
