package test;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

@Singleton
public class TestJPA {
    
    @EJB
    ParentEagerRepository parentEagerRepository;
    
    @EJB
    ParentLazyRepository parentLazyRepository;
    
    @EJB
    ParentCacheRepository parentCacheRepository;
    
    @EJB
    ParentGraphRepository parentGraphRepository;
    
    @EJB
    ChildRepository childRepository;
    
    @PostConstruct
    public void init()
    {
        //generateData();
    }
    
    @Schedule(second="*/30", minute="*",hour="*", persistent=false)
    public void runTests(){
        parentEagerRepository.runTest();
        parentLazyRepository.runTest();
        parentCacheRepository.runTest();
        parentGraphRepository.runTest();
    }
    
    public void generateData()
    {
        System.out.println("Creating data");
        Faker faker = new Faker();
        
        for(int i = 0; i < 100; i++)
        {
            ParentEager pe = new ParentEager(faker.name().firstName());
            parentEagerRepository.create(pe);
            ParentLazy pl = new ParentLazy(faker.name().firstName());
            parentLazyRepository.create(pl);
            ParentCache pc = new ParentCache(faker.name().firstName());
            parentCacheRepository.create(pc);
            ParentGraph pg = new ParentGraph(faker.name().firstName());
            parentGraphRepository.create(pg);
            
            for(int j = 0; j < 100; j++)
            {
                Child c = new Child(faker.name().firstName());
                c.addParentEager(pe);
                c.addParentLazy(pl);
                c.addParentCache(pc);
                c.addParentGraph(pg);
                childRepository.create(c);
            }
        }
        
        System.out.println("Done creating data");
    }
}
