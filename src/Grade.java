public abstract class Grade {
	protected Student Student;
	protected Class ClassToBeGraded;
	protected float frequency;
	protected float p1, p2, p3, l, s;
	protected boolean switchMode;
	protected boolean isApproved;

	public Grade(Student student, Class classToBeGraded, float p1, float p2, float p3, float l, float s,
			boolean switchMode) {
		this.Student = student;
		this.ClassToBeGraded = classToBeGraded;
		this.p1 = isValidGrade(p1);
		this.p2 = isValidGrade(p2);
		this.p3 = isValidGrade(p3);
		this.l = isValidGrade(l);
		this.s = isValidGrade(s);
		this.switchMode = switchMode;
	}

	public abstract String getFinalGrade();

	public float isValidGrade(float grade) {
		if (grade < 0 || grade > 10) {
			throw new IllegalArgumentException("Nota: " + grade + "deve ser entre 0-10");
		}
		return grade;
	}

	public float finalNumericGrade() {
		if (switchMode) {
			return (p1 + (p2 * 2) + (p3 * 3) + l + s) / 8;
		}
		return (p1 + p2 + p3 + l + s) / 5;
	}

	public String toCSV() {
		return String.format("%s,%s,%s",
				Student.getId(),
				ClassToBeGraded.getId(),
				getFinalGrade());
	}

	public Class getClassToBeGraded() {
		return ClassToBeGraded;
	}

	public float getFrequency() {
		return frequency;
	}

	public Student getStudent() {
		return Student;
	}

	public boolean checkswitchMode() {
		return switchMode;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public abstract float FinalGrade();
}
