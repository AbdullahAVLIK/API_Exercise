package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegresPojo {
    //#Pojo class olusturulurken izlenecek adimlar

    //1.Tum key'ler icin private variable'lar oluturuyoruz
    private String name;
    private String job;
    //2.Tum parametrelerle ve parametresiz constractor olusturuyoruz

    public RegresPojo(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public RegresPojo() {
    }
    //3. Public Getter ve Setter method'larini olusturuyoruz

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }


    //4. toString() method'unu olusturuyoruz


    @Override
    public String toString() {
        return "RegresPojo{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
