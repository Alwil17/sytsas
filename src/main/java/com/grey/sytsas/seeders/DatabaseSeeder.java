package com.grey.sytsas.seeders;

import com.grey.sytsas.entity.*;
import com.grey.sytsas.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

@Component
@Log4j2
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {
    private final SalleSportRepository salleSportRepository;
    private final EquipementRepository equipementRepository;
    private final AbonnementRepository abonnementRepository;
    private final AdherentRepository adherentRepository;
    private final CarriereRepository carriereRepository;
    private final HoraireRepository horaireRepository;
    private final DisponibiliteRepository disponibiliteRepository;
    private final EntraineurRepository entraineurRepository;

    @Override
    public void run(String... args) throws Exception {
        // Nettoyage des collections
        salleSportRepository.deleteAll();
        equipementRepository.deleteAll();
        abonnementRepository.deleteAll();
        adherentRepository.deleteAll();
        carriereRepository.deleteAll();
        entraineurRepository.deleteAll();
        horaireRepository.deleteAll();
        disponibiliteRepository.deleteAll();

        // Création d'équipements
        Equipement treadmill = Equipement.builder()
                .numEquip(1)
                .nomEquip("Tapis roulant")
                .fonctionEquip("Cardio")
                .quantite(5)
                .build();
        Equipement dumbbells = Equipement.builder()
                .numEquip(2)
                .nomEquip("Haltères")
                .fonctionEquip("Lifting")
                .quantite(10)
                .build();
        treadmill = equipementRepository.save(treadmill);
        dumbbells = equipementRepository.save(dumbbells);

        // Création d'un adhérent (héritant de Personne)
        Adherent adherent = Adherent.builder()
                .matricule(1001)
                .nom("BAKO")
                .prenom("Bouchara")
                .adresse("Sagbado, Lomé, TOGO")
                .telephone(99999999)
                .courriel("b.bako@gmail.com")
                .numMembre(1)
                .poids(70.5)
                .build();
        adherent = adherentRepository.save(adherent);

        // Création d'un abonnement reliant l'adhérent (composite SalleSport-Adherent)
        Abonnement abonnement = Abonnement.builder()
                .dateDebut(LocalDateTime.now().minusMonths(1))
                .dateFin(LocalDateTime.now().plusMonths(1))
                .actif(true)
                .adherent(adherent)
                .build();
        abonnement = abonnementRepository.save(abonnement);

        // Création d'un entraîneur (héritant de Personne)
        Entraineur entraineur = Entraineur.builder()
                .matricule(2001)
                .nom("AMEGANVI")
                .prenom("Kodjo")
                .adresse("Zanguera, Lomé, TOGO")
                .telephone(98765431)
                .courriel("k.ameganvi@sytsas.com")
                .numCoach(10)
                .specialite("Fitness")
                .dateEmb(LocalDateTime.now().minusYears(2))
                .salBase(3500.0)
                .build();
        entraineur = entraineurRepository.save(entraineur);

        // Création d'une carrière reliant l'entraîneur (composite SalleSport-Entraineur)
        Carriere carriere = Carriere.builder()
                .dateDebut(LocalDateTime.now().minusYears(1))
                .dateFin(LocalDateTime.now().plusYears(2))
                .entraineur(entraineur)
                .build();
        carriere = carriereRepository.save(carriere);

        // Création d'un horaire
        Horaire horaire = Horaire.builder()
                .debut(8.0)
                .fin(10.0)
                .build();
        horaire = horaireRepository.save(horaire);

        // Création d'une disponibilité liant l'entraîneur et l'horaire (via Disponibilite)
        Disponibilite disponibilite = Disponibilite.builder()
                .dateDispo(LocalDate.now())
                .horaires(Collections.singletonList(horaire))
                .build();
        disponibilite = disponibiliteRepository.save(disponibilite);

        // Mise à jour de l'entraîneur pour ajouter la disponibilité
        entraineur.setDisponibilites(Collections.singletonList(disponibilite));
        entraineur = entraineurRepository.save(entraineur);

        // Création d'une salle de sport et association avec équipements, abonnements et carrières
        SalleSport salleSport = SalleSport.builder()
                .numeroSalle(1)
                .adresseSalle("Adidogomé douane, Lomé, TOGO.")
                .capacite(50)
                .equipements(Arrays.asList(treadmill, dumbbells))
                .abonnements(Collections.singletonList(abonnement))
                .carrieres(Collections.singletonList(carriere))
                .build();
        salleSport = salleSportRepository.save(salleSport);

        System.out.println("Database seeding complete!");
    }
}

