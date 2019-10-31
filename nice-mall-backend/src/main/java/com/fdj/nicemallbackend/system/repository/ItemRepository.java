package com.fdj.nicemallbackend.system.repository;

import com.fdj.nicemallbackend.system.dto.Items;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Classname ItemRepository
 * @Description TODO
 * @Date 19-10-30 下午8:45
 * @Created by xns
 */
public interface ItemRepository extends ElasticsearchRepository<Items,Long> {
}
