package livemarket.business.b2bcart.services;


import livemarket.business.b2bcart.models.items.Item;
import livemarket.business.b2bcart.repositories.ItemRepository;
import livemarket.business.b2bcart.services.services.impl.ItemServiceImpl;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static java.nio.charset.Charset.forName;
import static org.powermock.api.support.membermodification.MemberMatcher.method;

//https://medium.com/@kamath.praveen1992/powermockito-in-springboot-574857b19f60
@SpringBootTest
@RunWith(PowerMockRunner.class)
@PrepareForTest({ItemServiceImpl.class})
public class ItemServiceImplTest {


    @Mock
    private ItemRepository itemRepository;
    @InjectMocks
    private ItemServiceImpl itemService = new ItemServiceImpl();
    private EasyRandom factory;


    public ItemServiceImplTest() {
    }

    @Before
    public void setUp() {


        EasyRandomParameters parameters = new EasyRandomParameters()
                .seed(123L)
                .objectPoolSize(100)
                .randomizationDepth(3)
                .charset(forName("UTF-8"))

                .stringLengthRange(5, 50)

                .collectionSizeRange(1, 10)
                .scanClasspathForConcreteTypes(true)
                .overrideDefaultInitialization(false)
                .ignoreRandomizationErrors(true);
        factory = new EasyRandom(parameters);
    }

    @After
    public void tearDown() {
        itemService = null;
    }

    @Test
    public void testServiceNotNull() {
        Assert.assertNotNull(itemService);

    }


    @Test
    public void updateProductById() throws Exception {
        Item product = factory.nextObject(Item.class);
        product.setId(1L);
        PowerMockito.when(itemRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(product));
        PowerMockito.when(itemRepository.save(Mockito.any(Item.class))).thenReturn(product);

        final ItemServiceImpl spiedItemService = PowerMockito.spy(itemService);


        //PowerMockito.doReturn(9).when(spiedItemService, "suma", ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong() );

        PowerMockito.doReturn(9).when(spiedItemService, method(ItemServiceImpl.class, "suma", Long.class, Long.class)).withArguments(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong());


        //PowerMockito.doReturn(9)
        //      .when(spiedItemService, "suma", 1L,1L);

        //final Integer entero = Whitebox.invokeMethod(spiedItemService, "suma", ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong());


        Assert.assertTrue(spiedItemService.updateProductById(1, product).isPresent());
    }


}
