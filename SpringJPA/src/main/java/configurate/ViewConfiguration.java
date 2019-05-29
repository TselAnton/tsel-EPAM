package configurate;

import generator.utils.BufferReaderGenerator;
import generator.utils.ScannerGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import view.impl.extend.*;

@Configuration
public class ViewConfiguration {

    @Autowired
    private ScannerGenerator scannerGenerator;

    @Autowired
    private BufferReaderGenerator bufferReaderGenerator;

    @Bean
    public AdminPanelView adminPanelView() {
        return new AdminPanelView(0, scannerGenerator);
    }

    @Bean
    public CatalogView catalogView() {
        return new CatalogView(1, scannerGenerator);
    }

    @Bean
    public ShoppingCartView shoppingCartView() {
        return new ShoppingCartView(2, scannerGenerator);
    }

    @Bean
    public SingInView singInView() {
        return new SingInView(3, scannerGenerator, bufferReaderGenerator);
    }

    @Bean
    public StoreMenuView storeMenuView() {
        return new StoreMenuView(4, scannerGenerator);
    }
}
