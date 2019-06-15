package design.composite;

/**
 * Describe class <code>Main</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public enum Main {
  MAIN;
  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(String[] args) {
    final Engineer ajay = new Engineer(1001L, "Ajay", "Developer", Department.ENG);
    final Engineer vijay = new Engineer(1002L, "Vijay", "SR. Developer", Department.ENG);
    final Engineer jay = new Engineer(1003L, "Jay", "Lead", Department.ENG);
    final Engineer martin = new Engineer(1004L, "Martin", "QA", Department.ENG);
    final Manager kim = new Manager(1005L, "Kim", "Manager", Department.ENG);
    final Engineer anders = new Engineer(1006L, "Andersen", "Developer", Department.ENG);
    final Manager niels = new Manager(1007L, "Niels", "Sr. Manager", Department.ENG);
    final Engineer robert = new Engineer(1008L, "Robert", "Developer", Department.ENG);
    final Manager rachelle = new Manager(1009L, "Rachelle", "Product Manager", Department.ENG);
    final Engineer shailesh = new Engineer(1010L, "Shailesh", "Engineer", Department.ENG);
    kim.manages(ajay);
    kim.manages(martin);
    kim.manages(vijay);

    niels.manages(jay);
    niels.manages(anders);
    niels.manages(shailesh);

    rachelle.manages(kim);
    rachelle.manages(robert);
    rachelle.manages(niels);

    final WorkLoader workLoad 
      = new WorkLoader("work.properties");

    workLoad.getWorkList()
      .stream().
      forEach(work -> { 
        rachelle.assignWork(rachelle, work);
      });
    rachelle.performWork();
  }
}
