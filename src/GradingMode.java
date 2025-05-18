public class GradingMode extends Grade {
	private float finalGrade;

	public GradingMode(Student student, Class classToBeGraded, float p1, float p2, float p3, float l, float s,
			boolean weightedMode) {
		super(student, classToBeGraded, p1, p2, p3, l, s, weightedMode);
		this.finalGrade = FinalGrade();
	}

	@Override
	public float FinalGrade() {
		finalGrade = finalNumericGrade();
		return finalGrade;
	}

	@Override
	public String getFinalGrade() {
		if (finalGrade < 1)
			return "SR";
		else if (finalGrade < 5)
			return "MI";
		else if (finalGrade < 7)
			return "MM";
		else if (finalGrade < 9)
			return "MS";
		else
			return "SS";
	}

}
