package ordination;

import java.time.LocalTime;

public class Dosis {
    private LocalTime tid;
    private double antal;
    private DagligFast dagligFast;
    private DagligSkaev dagligSkaev;

    public Dosis(LocalTime tid, double antal) {
        super();
        this.tid = tid;
        this.antal = antal;
    }

    public Dosis(LocalTime tid, double antal, DagligSkaev dagligSkaev) {
        this(tid, antal);
        this.dagligSkaev = dagligSkaev;
    }

    public Dosis(LocalTime tid, double antal, DagligFast dagligFast) {
        this(tid, antal);
        this.dagligFast = dagligFast;
    }

    public double getAntal() {
        return antal;
    }

    public void setAntal(double antal) {
        this.antal = antal;
    }

    public LocalTime getTid() {
        return tid;
    }

    public void setTid(LocalTime tid) {
        this.tid = tid;
    }

    @Override
    public String toString(){
        return "Kl: " + tid + "   antal:  " + antal;
    }

}
