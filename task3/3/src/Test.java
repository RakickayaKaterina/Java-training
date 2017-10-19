
public class Test {

	public static void main(String[] args) {
		Company company = new Company(6);
		company.addEmployee(new Director("Some Director", 5000.0));
		company.addEmployee(new JavaDeveloper("Developer 1", 500.0));
		company.addEmployee(new JavaDeveloper("Developer 2", 500.0));
		company.addEmployee(new JavaDeveloper("Developer 3", 520.0));
		company.addEmployee(new JavaDeveloper("Developer 4", 550.0));
		company.addEmployee(new ProjectManager("Some project manager", 700.0));
		Printer.show(company.getSalary());
	}

}
