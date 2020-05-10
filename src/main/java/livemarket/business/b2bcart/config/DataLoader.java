package livemarket.business.b2bcart.config;


import livemarket.business.b2bcart.models.items.Category;
import livemarket.business.b2bcart.models.items.Item;
import livemarket.business.b2bcart.models.rules.State;
import livemarket.business.b2bcart.models.users.Role;
import livemarket.business.b2bcart.models.users.RoleName;
import livemarket.business.b2bcart.models.users.User;
import livemarket.business.b2bcart.repositories.*;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Transactional
public class DataLoader implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    private StateRepository stateRepository;


    @Autowired
    private ItemRepository itemRepository;

    private EasyRandom factory;


    private Category category;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.createFactory();
        this.createUser();
        this.createCategory();
        this.createItems();
        this.createState();


    }

    private void createState() {
        State state = new State();
        state.setName("Inicial");
        state.setSequence(1);
        state.setSendEmilToClientUser(true);
        state.setCurrentState(null);

        State enviado_para_approacion = new State();
        enviado_para_approacion.setName("Enviado Para Aprobacion");
        enviado_para_approacion.setSequence(2);
        enviado_para_approacion.setSendEmilToClientUser(true);
        enviado_para_approacion.setCurrentState(null);

        enviado_para_approacion.addState(state);

        this.stateRepository.save(state);
        this.stateRepository.save(enviado_para_approacion);

    }

    private void createFactory() {
        EasyRandomParameters parameters = new EasyRandomParameters()
                .seed(123L)
                .objectPoolSize(100)
                .randomizationDepth(3)
                .charset(StandardCharsets.UTF_8)
                .stringLengthRange(5, 50)
                .collectionSizeRange(1, 10)
                .scanClasspathForConcreteTypes(true)
                .overrideDefaultInitialization(false)
                .ignoreRandomizationErrors(true);
        factory = new EasyRandom(parameters);
    }

    private void createItems() {
        this.itemRepository.saveAll(
                IntStream.range(1, 10).mapToObj(e ->
                        {
                            Item item = factory.nextObject(Item.class);
                            item.setPrice(1000d);
                            item.setQuality(1000d);
                            return item;

                        }

                ).map(item -> {
                    item.setCategory(this.category);
                    return item;
                }).collect(Collectors.toSet())
        );

    }

    private void createCategory() {
        Category category = new Category();
        category.setName("General");
        this.category = this.categoryRepository.save(category);

    }

    private void createUser() {
        User user = new User();
        roleRepository.save(new Role(RoleName.ROLE_USER));
        roleRepository.save(new Role(RoleName.ROLE_ADMIN));
        user.setPassword(passwordEncoder.encode("12345"));
        user.setUsername("admin");
        user.setEnabled(true);
        user.setRoles((List<Role>) this.roleRepository.findAll());

        this.userRepository.save(user);


    }
}

