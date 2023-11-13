package pt.com.halof.todolist.task;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import org.hibernate.annotations.CreationTimestamp;

/**
 * ID
 * Usuário (ID_usuario)
 * Descrição
 * Titulo
 * Data de termino
 * Prioridade
 *
 */

@Data
@Entity(name = "tb_tasks")
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;

    @Column(length = 50)
    private String title;

    private LocalDateTime startAt;
    private String priority;
    private LocalDateTime endAt;
    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setTitle(String title) throws Exception{
        if(title.length() > 50){
            throw new Exception("O campo title deve conter no máximo 50 caracteres");
        }
        this.title = title;
    }

}
