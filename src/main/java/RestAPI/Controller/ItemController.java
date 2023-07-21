package RestAPI.Controller;

import RestAPI.Handler.ItemNotFoundException;
import RestAPI.Model.Item;
import RestAPI.Persistence.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/items") // Metodo para regresar todos los datps que tiene la columna Item
    List<Item> all() {
        return itemRepository.findAll();
    }

    @GetMapping("/items/{id}") // Metodo que regresa un dato especifico mediante el Id (identificador)
    Item getById(@PathVariable Long id) {

        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    @PostMapping("/items") // Metodo que validar y guardar la estructura de datos introducida (Json o xml) en la DB
    Item createNew(@Valid @RequestBody Item newItem) {

        return itemRepository.save(newItem);
    }

    @DeleteMapping("/items/{id}")//Metodo para eliminar un dato especifico en la tabla Item mediante el Id (identificador)
    void delete(@PathVariable Long id) {
        itemRepository.deleteById(id);
    }

    @PutMapping("/items/{id}")//Medoto para Editar los valores que contengan el mismo Id (identificador) dentro de la DB
    Item updateOrCreate(@Valid @RequestBody Item newItem, @PathVariable Long id) {

        return itemRepository.findById(id)
                .map(item -> {
                    item.setName(newItem.getName());
                    item.setDescription(newItem.getDescription());
                    item.setPrice(newItem.getPrice());
                    return itemRepository.save(item);
                })
                .orElseGet(() -> {
                    newItem.setId(id);
                    return itemRepository.save(newItem);
                });
    }
}
