package pt.com.halof.todolist.user;


import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Modificador
 * public
 * private
 * protected
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    //Body
    @PostMapping("/")
    public ResponseEntity create(@RequestBody User user) {
       var user1 = this.userRepository.findByUsername(user.getUsername());

       if(user1 != null) {
           System.out.println("Usuario já existe");
           //Mensagem de erro
           //Status code
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario já existe");
       }

        var passwordHashred = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
       user.setPassword(passwordHashred);

       var userCreated = this.userRepository.save(user);
       return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
