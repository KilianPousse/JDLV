package kpss.jdlv.ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import kpss.jdlv.JeuDeLaVie;
import kpss.jdlv.Observateur;

import javax.swing.*;
import java.awt.*;

/**
 * Classe representant un graphique des donnees du jeu de la vie
 * @author Kilian POUSSE
 * @since 2025-03-21
 */
public class JDLVGraphique extends JPanel implements Observateur {

    /** Graphique (JFree-Chart) */
    private JFreeChart graph;

    /** Série de données */
    private XYSeries series;

    /** Collection des données */
    private XYSeriesCollection donnees;

    /** Nombre de generations enregistees */
    private int generation;

    /** Jeu de la vie */
    private JeuDeLaVie jeu;

    /** Rendu du graphique */
    private XYLineAndShapeRenderer rendu;

    /**
     * Constructeur du graphique
     * @param donnees
     */
    public JDLVGraphique(JeuDeLaVie jeu) {
        this.jeu = jeu;
        donnees = new XYSeriesCollection();
        series = new XYSeries("");
        generation = 0;

        for(Integer d: jeu.getDonnees()) {
            series.add(generation++, d);
        }

        this.donnees.addSeries(series);

        graph = ChartFactory.createXYLineChart(
                null,
                null,
                null,
                this.donnees,
                org.jfree.chart.plot.PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        XYPlot plot = graph.getXYPlot();
        plot.setBackgroundPaint(new Color(0, 0, 0, 0));
        plot.setDomainGridlinesVisible(false);
        plot.setRangeGridlinesVisible(false);
        plot.setOutlineVisible(false);

        rendu = new XYLineAndShapeRenderer(true, false);
        rendu.setSeriesPaint(0, Color.BLUE);
        plot.setRenderer(rendu);

        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        setLayout(new BorderLayout());
        add(new ChartPanel(graph), BorderLayout.CENTER);

        jeu.attacheObservateur(this);
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK)
        ));
    }

    /**
     * Ajout d'une génération aux données
     * @param valeur Nombre d'individu de la nouvelle genetation
     */
    public void ajoutValeur(int valeur) {
        series.add(generation++, valeur);
        repaint();
    }

    /**
     * Met à jour les données du graphique avec les nouvelles donnees dujeu
     */
    @Override
    public void actualise() {
        rendu.setSeriesPaint(0, jeu.getRegle().getCouleurGraph());

        series.clear();
        generation = 0;
        for(Integer d: jeu.getDonnees()) {
            series.add(generation++, d); 
        }
        repaint();
    }

    /**
     * Setter: affectation d'un jeu de la vie
     * @param jeu jeu de la vie
     */
    public void setJeu(JeuDeLaVie jeu) {
        this.jeu = jeu;
        jeu.attacheObservateur(this);
        actualise();
    }
}

