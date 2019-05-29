package factory;

public class Factory {
    private static Factory factory;

    public static Factory getInstance()
    {
        if(factory == null)
        {
            factory = new Factory();
        }
        return factory;
    }

    private DaoFactory daoFactory;
    private ServiceFactory serviceFactory;

    public void init()
    {
        this.daoFactory = new DaoReflectFactory();
        this.serviceFactory = new ServiceFactoryImp();
    }

    public DaoFactory getDaoFactory() {
        return this.daoFactory;
    }
    public ServiceFactory getServiceFactory() {
        return this.serviceFactory;
    }
}
