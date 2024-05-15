package br.com.senai.controllers;

import br.com.senai.models.User;
import br.com.senai.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @PostMapping(value="/createUser",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody User user){
        //Cria um novo objeto Users
        User newUser = new User();
        //Seta as propriedades do Coffee
        newUser.setName(user.getName());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        //Chama o método save para salvar o objeto no banco de dados
        return userRepository.save(newUser);
    }

    @PutMapping(value="/updateUser",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User user){
        User getUser = userRepository
                .findById(user.getId()).orElseThrow();
        User updateUser = new User();

        updateUser.setId(user.getId());
        updateUser.setName(user.getName());
        updateUser.setPassword(user.getPassword());
        updateUser.setEmail(user.getEmail());


        return userRepository.save(updateUser);
    }
    //Método deletar coffee
    @DeleteMapping(value="/deleteUser/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    //@PathVariable pega um valor passado junto a barra de endereço
    public User deleteUser(@PathVariable Long id){
        //Verificamos se existe o café no banco de dados procurando o id
        User getUser = userRepository.findById(id).orElseThrow();
        //chamamos o método .delete e passamos o café a ser deletado
        userRepository.delete(getUser);
        return getUser;
    }
}
