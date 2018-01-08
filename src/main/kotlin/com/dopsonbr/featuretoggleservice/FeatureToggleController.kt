package com.dopsonbr.featuretoggleservice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.Repository
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@RestController
class FeatureToggleController {

    @Autowired
    lateinit var storeFeatureToggleRepository: storeFeatureToggleRepository

    @Autowired
    lateinit var storeFeatureToggleRepository2: storeFeatureToggleRepository2

    @CrossOrigin
    @GetMapping(value = ["/featureToggles"])
    fun fetchFeatureToggles(@RequestParam storeNumber: String = "9798",
                            @RequestParam environment: String = "development",
                            @RequestParam productTeam: String = "sell"):
            List<String>? {
        val toggles: MutableIterable<StoreFeatureToggle>? = storeFeatureToggleRepository2
                .findByStoreNumberAndEnvironmentAndProductTeam(storeNumber, environment, productTeam)
        return toggles?.map { it.feature }

    }


    @CrossOrigin
    @PutMapping(path = ["/featureToggles"])
    @ResponseBody
    fun addNewUser(@RequestParam storeNumber: String,
                   @RequestParam environment: String,
                   @RequestParam productTeam: String,
                   @RequestParam feature: String): String {

        val n = StoreFeatureToggle()
        n.environment = environment
        n.productTeam = productTeam
        n.feature = feature
        n.storeNumber = storeNumber
        storeFeatureToggleRepository.save(n)
        return "Saved"
    }
}

interface storeFeatureToggleRepository : CrudRepository<StoreFeatureToggle, Long>


interface storeFeatureToggleRepository2 : Repository<StoreFeatureToggle, Long> {
    fun findByStoreNumberAndEnvironmentAndProductTeam(storeNumber: String,
                                                      environment: String,
                                                      productTeam: String):MutableIterable<StoreFeatureToggle>?
}

@Entity
class StoreFeatureToggle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    var storeNumber: String = ""
    var environment: String = ""
    var productTeam: String = ""
    var feature: String = ""
}
