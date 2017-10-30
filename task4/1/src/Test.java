import facade.CoursePlanningFacade;
import launcher.Launcher;

public class Test {

	public static void main(String[] args) {
		Launcher launcher = new Launcher();
		CoursePlanningFacade facade = new CoursePlanningFacade(launcher.getServiceLect(), launcher.getServiceCource(), launcher.getServiceTime(), launcher.getServiceStud());
	}

}
