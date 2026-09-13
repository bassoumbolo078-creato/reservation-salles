package com.ism;

public final class Salle extends AbstractEntity {

    private String nom;
    private String batiment;
    private int capacite;
    private TypeSalle type;
    private boolean active;

    public Salle(Long id, String nom, String batiment, int capacite, TypeSalle type) {
        super(id);
        this.nom = nom;
        this.batiment = batiment;
        this.capacite = capacite;
        this.type = type;
        this.active = true;
    }

    public boolean peutAccueillir(int nombrePersonnes) {
        return active && nombrePersonnes <= capacite;
    }

    public String getNom() {
        return nom;
    }

    public String getBatiment() {
        return batiment;
    }

    public int getCapacite() {
        return capacite;
    }

    public TypeSalle getType() {
        return type;
    }

    public boolean isActive() {
        return active;
    }

    public void changerCapacite(int nouvelleCapacite) {
        if (nouvelleCapacite <= 0) {
            throw new IllegalArgumentException("Capacité invalide");
        }
        this.capacite = nouvelleCapacite;
    }
}