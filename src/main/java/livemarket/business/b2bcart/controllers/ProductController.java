package livemarket.business.b2bcart.controllers;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import livemarket.business.b2bcart.errors.ValidationErrorBuilder;
import livemarket.business.b2bcart.models.files.FileItemDto;
import livemarket.business.b2bcart.models.items.Item;
import livemarket.business.b2bcart.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@Secured({
        "ROLE_ADMIN",
        "ROLE_USER"
})
@RequestMapping("/api/v1/products")
public class ProductController {
//https://phoenixnap.com/kb/spring-boot-validation-for-rest-services


    @Autowired
    private ItemService service;


    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization ", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> queryByPage(Pageable pageable) {
        //https://dzone.com/articles/conditional-pagination-and-sorting-using-restful-w
        Page<Item> pageInfo = service.findAll(pageable);
        if (pageInfo.getContent().isEmpty()) {
            return new ResponseEntity<Page<Item>>(pageInfo, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Item>>(pageInfo, HttpStatus.OK);

    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization ", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @GetMapping(value = "{id}/")
    public ResponseEntity<?> findById(@PathVariable long id) {

        Optional<Item> productOptional = service.findById(id);
        if (productOptional.isPresent()) {
            return new ResponseEntity<Item>(productOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Item>(productOptional.get(), HttpStatus.NOT_FOUND);
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization ", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @PostMapping
    public ResponseEntity<?> creatProduct(@Valid @RequestBody Item product, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        return new ResponseEntity<Item>(service.createProduct(product), HttpStatus.OK);

    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization ", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @PutMapping("{id}/")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") long id, @Valid @RequestBody Item productForUpdate, Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        Optional<Item> optionalProduct = this.service.findById(id);
        if (optionalProduct.isPresent()) {
            Item product = optionalProduct.get();
            product.setName(productForUpdate.getName());
            product.setPrice(productForUpdate.getPrice());
            return new ResponseEntity<Item>(service.updateProduct(product), HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Data found", HttpStatus.NOT_FOUND);
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization ", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @PatchMapping("{id}/")
    public ResponseEntity<?> updateEmployeeByUser(@RequestParam("id") long id, @Valid @RequestBody Item product, Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        Optional<Item> optionalProduct = service.updateProductById(id, product);
        if (optionalProduct.isPresent()) {
            return new ResponseEntity<Item>(optionalProduct.get(), HttpStatus.OK);
        }
        return new ResponseEntity<String>("Without updates", HttpStatus.BAD_REQUEST);
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization ", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    @DeleteMapping(value = "{id}/")
    public void deleteEmployeeById(@PathVariable long id) {
        service.deleteProductById(id);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization ", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })

    @PostMapping("/photos/add")
    public ResponseEntity<FileItemDto> addPhoto(

            @RequestParam("title") String title,
            @RequestParam("itemPk") Integer itemPk,
            @RequestParam("order") Integer order,
            @RequestParam("isCovert") Boolean isCovert,
            @RequestParam("file") MultipartFile file, Model model) throws IOException {


        FileItemDto fileItemDto = service.addFileItem(title, order, itemPk, isCovert, file);
        return new ResponseEntity<FileItemDto>(fileItemDto, HttpStatus.OK);
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization ", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })

    @GetMapping("/photos/{id}")
    public ResponseEntity<List<FileItemDto>> getPhoto(@PathVariable String id) {
        List<FileItemDto> fileItemDto = service.getFileByItemPk(id);
        return new ResponseEntity<List<FileItemDto>>(fileItemDto, HttpStatus.OK);
    }


}
