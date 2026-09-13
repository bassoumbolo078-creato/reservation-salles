package com.ism;

import java.time.LocalDate;

public final class Reservation extends AbstractEntity {

    private Salle salle;
    private LocalDate dateReservation;
    private StatutReservation statut;

    public Reservation(Long id, Salle salle, LocalDate dateReservation) {
        super(id);
        this.salle = salle;
        this.dateReservation = dateReservation;
        this.statut = StatutReservation.EN_ATTENTE;
    }

    public Salle getSalle() {
        return salle;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public StatutReservation getStatut() {
        return statut;
    }

    public void confirmer() {
        this.statut = StatutReservation.CONFIRMEE;
    }

    public void annuler() {
        this.statut = StatutReservation.ANNULEE;
    }
}