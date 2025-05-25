public class GradingMode extends Grade {
	private float finalGrade;

	public GradingMode(Student student, Class classToBeGraded, float p1, float p2, float p3, float l, float s,
			boolean weightedMode) {
		super(student, classToBeGraded, p1, p2, p3, l, s, weightedMode);
		this.finalGrade = FinalGrade();
		this.isApproved = makeApproval();
	}

	@Override
	public float FinalGrade() {
		finalGrade = finalNumericGrade();
		return finalGrade;
	}

	protected boolean makeApproval() {
		return (super.getFrequency() >= 75) && (finalGrade >= 5);

	}

	@Override
	public String getFinalGrade() {
		if (frequency < 75)
			return "SR";
		else if (finalGrade < 1)
			return "SR";
		else if (finalGrade < 5)
			return "MI";
		else if (finalGrade < 7 || frequency >= 75)
			return "MM";
		else if (finalGrade < 9 || frequency >= 75)
			return "MS";
		else
			return "SS";
	}

}
