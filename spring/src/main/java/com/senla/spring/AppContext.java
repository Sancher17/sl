package com.senla.spring;

import com.senla.api.dao.IBookDao;
import com.senla.api.dao.IOrderDao;
import com.senla.api.dao.IRequestDao;
import com.senla.api.facade.IBookShop;
import com.senla.api.fileworker.IFileWorker;
import com.senla.api.fileworker.exports.IExportToCsv;
import com.senla.api.fileworker.imports.IImportFromCsv;
import com.senla.api.services.IServiceBook;
import com.senla.api.services.IServiceExit;
import com.senla.api.services.IServiceOrder;
import com.senla.api.services.IServiceRequest;
import com.senla.facade.BookShop;
import com.senla.fileworker.exports.ExportToCsv;
import com.senla.fileworker.imports.ImportFromCsv;
import com.senla.fileworker.startModule.FileWorker;
import com.senla.hibernate.BookDao;
import com.senla.hibernate.OrderDao;
import com.senla.hibernate.RequestDao;
import com.senla.propertiesmodule.IPropertyHolder;
import com.senla.propertiesmodule.PropertyHolder;
import com.senla.services.ServiceBook;
import com.senla.services.ServiceExit;
import com.senla.services.ServiceOrder;
import com.senla.services.ServiceRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
public class AppContext {

    private IPropertyHolder property = new PropertyHolder();

    @Bean
    public IExportToCsv exportToCsv() {
        return new ExportToCsv();
    }

    @Bean
    public IImportFromCsv importFromCsv() {
        return new ImportFromCsv();
    }

    @Bean
    public IFileWorker fileWorker() {
        return new FileWorker(exportToCsv(), importFromCsv(), propertyHolder());
    }

    @Bean
    public IBookDao bookDao() {
        return new BookDao();
    }

    @Bean
    public IOrderDao orderDao() {
        return new OrderDao();
    }

    @Bean
    public IRequestDao requestDao() {
        return new RequestDao();
    }

    @Bean
    public IServiceBook serviceBook() {
        return new ServiceBook(bookDao(), requestDao(), fileWorker());
    }

    @Bean
    public IServiceOrder serviceOrder() {
        return new ServiceOrder(orderDao(), bookDao(), fileWorker());
    }

    @Bean
    public IServiceRequest serviceRequest() {
        return new ServiceRequest(requestDao(), fileWorker());
    }

    @Bean
    public IServiceExit serviceExit() {
        return new ServiceExit();
    }

    @Bean
    public IPropertyHolder propertyHolder() {
        return new PropertyHolder();
    }

    @Bean
    public IBookShop bookShop() {
        return new BookShop(serviceBook(), serviceOrder(),
                serviceRequest(), serviceExit(), propertyHolder()) {
        };
    }

//    private <T> T createInstance(Class<T> anInterface) {
//        try {
//            Class<?> clazz = Class.forName(property.loadBean(anInterface));
//            Constructor<?> constructor = clazz.getConstructors()[0];
//            if (constructor.getParameterCount() > 0) {
//                Object[] dependencies = resolve(constructor);
//                Object obj = newInstance(constructor, dependencies);
//                return (T) obj;
//            }
//            return (T) clazz.newInstance();
//        } catch (ReflectiveOperationException e) {
//            log.error(NO_SUCH_CLASS + e);
//            return null;
//        } catch (ArrayIndexOutOfBoundsException e) {
//            return getSingelton(anInterface);
//        }
//    }
//    @SuppressWarnings("unchecked")
//    private <T> T createInstance(Class<T> anInterface) {
//        try {
//            Class<?> clazz = Class.forName(property.loadBean(anInterface));
//            return (T) clazz.newInstance();
//        } catch (ReflectiveOperationException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private <T> T createInstanceWithParameters(Class<T>... anInterface) {
//        Class [] obj = anInterface;
//        for( int i = 0; i < anInterface.length; i++){
//            try {
//                Class<?> clazz = Class.forName(property.loadBean(obj[i]));
//                return (T) clazz.newInstance();
//            } catch (ReflectiveOperationException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//

//       /**реализация из классса DI */
//    public <T> T createInst(Class<T> anInterface) {
//        try {
//            Class<?> clazz = Class.forName(property.loadBean(anInterface));
//            Constructor<?> constructor = clazz.getConstructors()[0];
//            if (constructor.getParameterCount() > 0) {
//                Object[] dependencies = resolve(constructor);
//                Object obj = newInstance(constructor, dependencies);
//                return (T) obj;
//            }
//            return (T) clazz.newInstance();
//        } catch (ReflectiveOperationException e) {
////            log.error(NO_SUCH_CLASS + e);
//            return null;
//        } catch (ArrayIndexOutOfBoundsException e) {
//            return getSingelton(anInterface);
//        }
//    }
//
//    private <T> T build(Class<T> aClass) {
//        try {
//            Constructor<?> constructor = aClass.getConstructors()[0];
//            if (constructor.getParameterTypes().length > 0) {
//                Object[] dependencies = resolve(constructor);
//                Object obj = newInstance(constructor, dependencies);
//                return (T) obj;
//            }
//            Object obj = newInstance(constructor);
//            return (T) obj;
//        } catch (ArrayIndexOutOfBoundsException e) {
//            return getSingelton(aClass);
//        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
////            log.error(PROBLEM_WITH_DEPENDENCY + e);
//        }
//        return null;
//    }
//
//    private  <T> T getSingelton(Class<T> aClass) {
//        try {
//            Class<?> clazz = Class.forName(property.loadBean(aClass));
//            return (T) clazz.getMethod("getInstance").invoke(null);
//        } catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException | NoSuchMethodException e) {
////            log.error(PROBLEM_WITH_SINGELTON_DEPENDENCY + e);
//        }
//        return null;
//    }
//
//    private <T> T newInstance(Constructor<?> constructor, Object... dependencies) throws IllegalAccessException, InvocationTargetException, InstantiationException {
//        return (T) constructor.newInstance(dependencies);
//    }
//
//    private  Object[] resolve(Constructor<?> constructor) {
//        List<Object> dependencies = new LinkedList<>();
//        for (Class<?> dependency : constructor.getParameterTypes()) {
//            dependencies.add(build(dependency));
//        }
//        return dependencies.toArray();
//    }

}
