package pt.com.halof.todolist.user;
import jakarta.persistence.Column;
import lombok.Data;
import jakarta.persistence.Entity;
import java.util.UUID;
import jakarta.persistence.Id;
import lombok.Generated;
import jakarta.persistence.GeneratedValue;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Data
@Entity(name= "tb_users")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    //@Getter em cima do atributo se eu apenas quiser criar num atributo
    private String name;
    @Column(unique = true)//A coluna do username so pode ter 1 valor igual
    private String username;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Override
    public String toString(){
        return "A conta de nome: " + name + " " + username + " foi criada com sucesso.";
    }
}
