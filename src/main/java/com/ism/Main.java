package com.ism;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Salle> salles = new ArrayList<>();

        salles.add(new Salle(1L, "A101", "Batiment A", 30, TypeSalle.COURS));
        salles.add(new Salle(2L, "A102", "Batiment A", 25, TypeSalle.TP));
        salles.add(new Salle(3L, "B201", "Batiment B", 100, TypeSalle.AMPHITHEATRE));
        salles.add(new Salle(4L, "B202", "Batiment B", 15, TypeSalle.REUNION));
        salles.add(new Salle(5L, "C301", "Batiment C", 40, TypeSalle.COURS));

        System.out.println("=== Liste des salles ===");
        for (Salle salle : salles) {
            System.out.println(
                "Salle " + salle.getNom()
                + " (" + salle.getBatiment() + ") - "
                + salle.getCapacite() + " places - "
                + "Type: " + salle.getType()
                + " - Active: " + salle.isActive()
            );
        }

        List<Reservation> reservations = new ArrayList<>();

        reservations.add(new Reservation(1L, salles.get(0), LocalDate.of(2026, 9, 20)));
        reservations.add(new Reservation(2L, salles.get(2), LocalDate.of(2026, 9, 21)));

        System.out.println();
        System.out.println("=== Liste des reservations ===");
        for (Reservation reservation : reservations) {
            System.out.println(
                "Reservation #" + reservation.getId()
                + " - Salle: " + reservation.getSalle().getNom()
                + " - Date: " + reservation.getDateReservation()
                + " - Statut: " + reservation.getStatut()
            );
        }
    }
}