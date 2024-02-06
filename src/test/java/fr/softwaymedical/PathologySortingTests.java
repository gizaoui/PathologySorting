package fr.softwaymedical;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import fr.softwaymedical.exception.HealthIdException;
import fr.softwaymedical.model.DiagnosticEntity;
import fr.softwaymedical.model.PatientEntity;
import fr.softwaymedical.model.PlanningEntity;
import fr.softwaymedical.repository.PlanningRepository;
import fr.softwaymedical.resquest.DiagnosticDTO;
import fr.softwaymedical.resquest.PatientDTO;
import fr.softwaymedical.service.DiagnosticService;
import fr.softwaymedical.service.Pathology;
import fr.softwaymedical.service.PatientService;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class PathologySortingTests {

    private static final Logger logger = LogManager.getLogger(PathologySortingTests.class);

    @Autowired
    private PatientService patientService;

    @Autowired
    private DiagnosticService diagnosticService;

    @Autowired
    private PlanningRepository planningRepository;

    @Test
    public void test01() {

        logger.info("_________  INIT TEST   _________");
        DiagnosticEntity diagnosticEntity = null;
        List<PlanningEntity> arrPlanning = null;

        logger.info("_________  PatientEntity   _________");
        PatientEntity patientEntity = this.patientService.save(new PatientDTO("123456789", "sofia.mitchell@hotmail.fr", "Sofia", "Mitchell", 38,
                "female", "+1 (800) 403-3710", "232 Cheever Place, Ruckersville, Pennsylvania, 4623"));
        assertEquals(patientEntity.getSocialSecurity(), "123456789");

        logger.info("_________  DiagnosticEntity : health Id = 3 (cardiology)  _________");
        planningRepository.deleteAll();
        diagnosticEntity = this.diagnosticService.save(new DiagnosticDTO("123456789", Date.valueOf(LocalDate.of(2023, Month.JANUARY, 1)), 3),
                patientEntity);
        arrPlanning = new ArrayList<>(diagnosticEntity.getPlanning());
        if (arrPlanning.size() == 1) {
            assertEquals(arrPlanning.get(0).getService(), Pathology.CARDIOLOGY.getValue());
        } else {
            fail("update not performed for health Id = 3");
        }

        logger.info("_________  DiagnosticEntity : health Id = 5 (traumatology)  _________");
        planningRepository.deleteAll();
        diagnosticEntity = this.diagnosticService.save(new DiagnosticDTO("123456789", Date.valueOf(LocalDate.of(2023, Month.JANUARY, 1)), 5),
                patientEntity);
        arrPlanning = new ArrayList<>(diagnosticEntity.getPlanning());
        if (arrPlanning.size() == 1) {
            assertEquals(arrPlanning.get(0).getService(), Pathology.TRAUMATOLOGY.getValue());
        } else {
            fail("update not performed for health Id = 15");
        }

        logger.info("_________  DiagnosticEntity : health Id = 15 (cardiology & traumatology)  _________");
        planningRepository.deleteAll();
        boolean cardiology = false, traumatology = false;
        diagnosticEntity = this.diagnosticService.save(new DiagnosticDTO("123456789", Date.valueOf(LocalDate.of(2023, Month.JANUARY, 1)), 15),
                patientEntity);
        for (Iterator<PlanningEntity> it = diagnosticEntity.getPlanning().iterator(); it.hasNext();) {
            PlanningEntity p = it.next();
            if (p.getService().equals(Pathology.CARDIOLOGY.getValue())) {
                cardiology = true;
            }
            if (p.getService().equals(Pathology.TRAUMATOLOGY.getValue())) {
                traumatology = true;
            }
        }
        assertTrue(cardiology && traumatology);

        logger.info("_________  DiagnosticEntity : health Id = 2 (exception: 'Health Id is invalid')  _________");
        planningRepository.deleteAll();
        try {
            this.diagnosticService.save(new DiagnosticDTO("123456789", Date.valueOf(LocalDate.of(2023, Month.JANUARY, 1)), 2), patientEntity);
        } catch (HealthIdException e) {
            logger.info(e.getMessage());
            assertThat(e.getMessage()).contains("Health Id is invalid");
        }

    }

}
