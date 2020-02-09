package com.ruwork.web.filter;


import com.ruwork.web.model.SaleModel;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Log
@Service
public class SaleAuthenticationFilter {
    private static final ConcurrentHashMap<Long, SaleModel> checkCache = new ConcurrentHashMap(100);



        @PostConstruct
        public void init(){

            Thread jobWorker = new Thread(new CleanWorker());
            jobWorker.start();

        }

        public SaleModel filter(Long ticket){
            return     checkCache.get(ticket);

        }

        public void setCheckCache(Long ticket, SaleModel saleModel){

            checkCache.put(ticket, saleModel);
        }
    class CleanWorker implements  Runnable {


        @Override
        public void run() {

            while (true){
                try {
                    Thread.sleep(1000l);
                    if (checkCache.isEmpty()) return ;
                    long currentTimeMillis =  System.currentTimeMillis()+(5*60*1000); //5分钟缓存
                    Set<Long> timeSet =  checkCache.keySet();
                    List<Long> expireList =   timeSet.stream().filter(e->(e<=currentTimeMillis)).collect(Collectors.toList());
                    for(Long expireKey :expireList){
                        checkCache.remove(expireKey);
                    }
                } catch (InterruptedException e) {
                    log.warning(e.getMessage());
                }

            }


        }
    }


}
