package Members;
import java.time.Year;

public class RecLetter {
    private final StringBuilder sb;
    private final int year = Year.now().getValue();

    public RecLetter() {
        sb = new StringBuilder();
        sb.append("Dear Admissions Committee,\n\n");
        sb.append("I am writing to recommend ");
    }

    public void compile(Student student){
        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        int yearsKnown = year - student.getSemYear();
        sb.append(String.format("%s %s for admission to your university. ", firstName, lastName));
        sb.append(String.format("I have known %s for %d years. ", firstName, yearsKnown));

    }

    public String getText(){
        return sb.toString();
    }

}
