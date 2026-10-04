package tn.esprit.backend.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import tn.esprit.backend.entity.Projet;
import tn.esprit.backend.repository.ProjetRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProjetServiceImplTest {

    @Mock
    private ProjetRepository projetRepository;

    @InjectMocks
    private ProjetServiceImpl projetService;

    private Projet buildProjet(Long id, String sujet) {
        Projet p = new Projet();
        p.setId(id);
        p.setSujet(sujet);
        return p;
    }

    @Test
    void testAddProjet() {
        Projet projet = buildProjet(1L, "Gestion des projets");
        when(projetRepository.save(any(Projet.class))).thenReturn(projet);

        Projet result = projetService.addProjet(projet);

        assertNotNull(result);
        assertEquals("Gestion des projets", result.getSujet());
    }

    @Test
    void testGetProjetById() {
        Projet projet = buildProjet(1L, "DevOps");
        when(projetRepository.findById(1L)).thenReturn(Optional.of(projet));

        Projet result = projetService.getProjetById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("DevOps", result.getSujet());
    }

    @Test
    void testGetAllProjets() {
        List<Projet> projets = List.of(buildProjet(1L, "Projet A"), buildProjet(2L, "Projet B"));
        when(projetRepository.findAll()).thenReturn(projets);

        assertEquals(2, projetService.getAllProjets().size());
    }

    @Test
    void testDeleteProjet() {
        when(projetRepository.findById(1L)).thenReturn(Optional.of(buildProjet(1L, "A supprimer")));

        assertDoesNotThrow(() -> projetService.deleteProjet(1L));
    }
}
