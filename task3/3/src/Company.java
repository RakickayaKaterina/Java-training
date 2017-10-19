
public class Company {
	private Employee[] employees;

	public Company(int countEmployees) {
		employees = new Employee[countEmployees];
	}

	public void addEmployee(Employee pEmployee) {
		if (Checker.existPosition(employees)) {
			int position = Checker.getFreePosition(employees);
			employees[position] = pEmployee;
		} else
			Printer.show("The Addition is impossible");
	}

	public double getSalary() {
		double result = 0;
		for (Employee employee : employees) {
			result += employee.getSalary();
		}
		return result;
	}
}
