import ee.ut.dendroloj.Dendrologist;
import static ee.ut.dendroloj.Dendrologist.*;


public class Main {

    static void kuvaKahendpuu(KOTipp juurTipp) {
       Dendrologist.drawBinaryTree(juurTipp, t -> Integer.toString(t.väärtus), t -> t.v, t -> t.p);}


    public static void main(String[] args) {
        KOTipp juur = AVLPuu(5);

        märgendaPuu(juur);
        int tippe = tippe(juur);
        int[] arvud =  new int[tippe];
        for (int i=0;i<tippe ;i++) arvud[i] = i;
        täidaKOP(juur,0,arvud);

        //KOTipp() vähim = leiaVähim(juur);
        KOTipp otsitav = otsiKirjet(juur, 5);
        kuvaKahendpuu(juur);
        //kuvaKahendpuu(otsitav);

        kuvaKahendpuu(lisaKirje(juur, 15));
        System.out.println("Hello world!");
    }


    public static KOTipp AVLPuu(int n){
        System.out.println(n);
    if (n ==0) return null;
    if (n ==1) return  new KOTipp(0);
    KOTipp juur = new KOTipp(0);

    juur.v = AVLPuu(n - (Math.random() > 0.5 ? 1 : 2));
    juur.p = AVLPuu(n - (Math.random() > 0.5 ? 1 : 2));
    return juur;
    }

    public static void täidaKOP(KOTipp juur ,int vasakPiir, int[] arvud){
        if (juur == null)return;
        int vasakud = tippe(juur.v);
       juur.väärtus = arvud[vasakPiir + vasakud];
       täidaKOP(juur.v, vasakPiir,arvud);
       täidaKOP(juur.p, vasakPiir + vasakud+1, arvud);
    }

    public static void märgendaPuu(KOTipp juur){
     if (juur == null) return;
     märgendaPuu(juur.v);
     märgendaPuu(juur.p);
     if (juur.v != null) juur.x = juur.v.x + 1 + tippe(juur.v.p);
    }

    public static int tippe(KOTipp juur){
        if (juur == null) return  0;
        return 1+ tippe(juur.v) + tippe(juur.p);
    }
    public static KOTipp leiaVähim(KOTipp juur){
        return null;
    }
    public static KOTipp otsiKirjet(KOTipp juur, int kirje){

        if (juur == null || juur.väärtus == kirje) return juur;

        //otsime paremast harust

        //otsime vasakust harutst
        if (kirje < juur.väärtus){ return otsiKirjet(juur.v, kirje);}

        return otsiKirjet(juur.p, kirje);
    }

    public static KOTipp lisaKirje(KOTipp juur, int kirje){
            if (juur == null){
                 return new KOTipp(kirje);}

        if (kirje < juur.väärtus){
            juur.v = lisaKirje(juur.v, kirje);}
        if (kirje > juur.väärtus){
            juur.p = lisaKirje(juur.p, kirje);
        }
        return juur;
    }

    public static KOTipp eemaldaKirje(KOTipp juur, int kirje){
        if (juur == null) return  null;
        if (juur.väärtus == kirje){
            if (juur.v == null && juur.p == null )return null;
            if (juur.v != null && juur.p != null ){
                KOTipp jargmine = juur.p;
                while (jargmine.v != null) jargmine = jargmine.v;
                juur.väärtus =jargmine.väärtus;
                juur.p = eemaldaKirje(juur.p, juur.väärtus);

            }
            if (juur.v != null) return juur.v;
            if (juur.p != null) return juur.p;

        }
        else if ( kirje < juur.väärtus){
            juur.v = eemaldaKirje(juur.v, kirje);
        }
        else if ( kirje > juur.väärtus){
            juur.p = eemaldaKirje(juur.p, kirje);
        }

        return juur;
    }
}