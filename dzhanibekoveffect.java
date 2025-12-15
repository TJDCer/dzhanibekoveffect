/*
 * dzhanibekoveffect.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Dec 15 2025, 19:34 by COMSOL 6.4.0.293. */
public class dzhanibekoveffect {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("C:\\Users\\xdc\\Downloads");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").activate("mbd", true);

    model.geom()
         .load(new String[]{"part1"}, "RF_Module\\Connectors\\connector_edge_launch_elf40_002.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().remove("pi1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.1, 0.01, 0.05});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.1, 0.01, 0.025});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new double[]{0, 0, 0.05});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "0", "0.0"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("mbd").create("rd1", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd1").selection().set(1);
    model.component("comp1").physics("mbd").feature("rd1").set("rho_mat", "userdef");
    model.component("comp1").physics("mbd").feature("rd1").set("rho", 1000);
    model.component("comp1").physics("mbd").feature("rd1").set("InitialValueType", "locallyDefined");
    model.component("comp1").physics("mbd").feature("rd1").feature("init1").set("omega", new int[]{0, 0, 10});
    model.component("comp1").physics("mbd").feature("rd1").feature("init1").set("Omega", new int[]{0, 0, 1});
    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("CenterOfRotationType", "CentroidOfSelectedEntities");

    model.study("std1").feature("time").set("tlist", "range(0,0.1,10)");

    model.sol().create("sol1");
    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.10356157588603991");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.10356157588603991");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.1,10)");
    model.sol("sol1").feature("t1").set("plot", "off");
    model.sol("sol1").feature("t1").set("plotgroup", "Default");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("Displacement (mbd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("Surface");
    model.result("pg1").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1").label("Deformation");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("Velocity (mbd)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").label("Volume");
    model.result("pg2").feature("vol1").set("expr", "mod(dom,10)");
    model.result("pg2").feature("vol1").set("unit", "1");
    model.result("pg2").feature("vol1").set("colortable", "Cyclic");
    model.result("pg2").feature("vol1").set("colorlegend", false);
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg2").feature("vol1").feature().create("def1", "Deform");
    model.result("pg2").feature("vol1").feature("def1").label("Deformation");
    model.result("pg2").feature().create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").label("Arrow Line");
    model.result("pg2").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY", "mbd.u_tZ"});
    model.result("pg2").feature("arwl1").set("placement", "elements");
    model.result("pg2").feature("arwl1").set("data", "parent");
    model.result("pg2").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg2").feature("arwl1").feature("def1").label("Deformation");
    model.result().remove("pg2");
    model.result().remove("pg1");

    model.component("comp1").physics("mbd").feature("rd1").feature("init1").feature("crb1").selection()
         .set(1, 2, 3, 4, 5, 6);

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.10356157588603991");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.10356157588603991");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.1,10)");
    model.sol("sol1").feature("t1").set("plot", "off");
    model.sol("sol1").feature("t1").set("plotgroup", "Default");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("Displacement (mbd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("Surface");
    model.result("pg1").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1").label("Deformation");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("Velocity (mbd)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").label("Volume");
    model.result("pg2").feature("vol1").set("expr", "mod(dom,10)");
    model.result("pg2").feature("vol1").set("unit", "1");
    model.result("pg2").feature("vol1").set("colortable", "Cyclic");
    model.result("pg2").feature("vol1").set("colorlegend", false);
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg2").feature("vol1").feature().create("def1", "Deform");
    model.result("pg2").feature("vol1").feature("def1").label("Deformation");
    model.result("pg2").feature().create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").label("Arrow Line");
    model.result("pg2").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY", "mbd.u_tZ"});
    model.result("pg2").feature("arwl1").set("placement", "elements");
    model.result("pg2").feature("arwl1").set("data", "parent");
    model.result("pg2").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg2").feature("arwl1").feature("def1").label("Deformation");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 101, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 100, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 99, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 98, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 97, 0);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 101, 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def1").active(false);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def1").active(true);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def1").active(false);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def1").active(true);
    model.result("pg1").run();

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"0", "0", ".1"});

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.10356157588603991");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.10356157588603991");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.1,10)");
    model.sol("sol1").feature("t1").set("plot", "off");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 3, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 4, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 5, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 6, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 7, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 8, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 9, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 10, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 11, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 12, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 13, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 14, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 15, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 16, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 17, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 18, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 19, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 101, 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();

    model.study("std1").feature("time").set("geometricNonlinearity", true);

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.10356157588603991");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.10356157588603991");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.1,10)");
    model.sol("sol1").feature("t1").set("plot", "off");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 3, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 4, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 5, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 6, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 7, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 8, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 9, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 10, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 11, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 12, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 13, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 14, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 15, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 16, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 17, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 18, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 19, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 20, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 21, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 22, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 23, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 24, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 25, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 26, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 27, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 28, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 29, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 30, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 31, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 32, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 33, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 34, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 35, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 101, 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "mbd.disp*0 + 1");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 3, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 4, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 5, 0);
    model.result("pg1").run();

    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.1, 0.005, 0.025});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.1, 0.005, 0.04});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.1,100)");
    model.study("std1").feature("time").set("plot", true);

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.10781929326423914");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");

    return model;
  }

  public static Model run2(Model model) {
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.10781929326423914");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.1,100)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.result("pg1").run();

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"1e-8", "1e-8", ".1"});

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.10781929326423914");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.10781929326423914");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.1,100)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.study("std1").feature("time").set("tlist", "range(0,1,240)");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.10781929326423914");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.10781929326423914");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,1,240)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.study("std1").feature("time").set("tlist", "range(0,2,1800)");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.10781929326423914");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.10781929326423914");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,2,1800)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"1e-8", "1e-8", "1"});

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.10781929326423914");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.10781929326423914");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,2,1800)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"1e-8", "1e-8", ".1"});

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.10781929326423914");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.10781929326423914");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,2,1800)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"0", "0", ".1"});

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.10781929326423914");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.10781929326423914");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,2,1800)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.10781929326423914");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.10781929326423914");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,2,1800)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"0", "0", "2*pi"});

    model.study("std1").feature("time").set("tlist", "range(0,0.05,20)");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");

    return model;
  }

  public static Model run3(Model model) {
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.10781929326423914");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.10781929326423914");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.05,20)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.1, 0.005, 0.06});
    model.component("comp1").geom("geom1").runPre("fin");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.11672617529928753");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.11672617529928753");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.05,20)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.1, 0.005, 0.075});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.5, 0.005, 0.075});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.15, 0.005, 0.075});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.05,45)");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.1677796173556252");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.1677796173556252");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.05,45)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "Y");
    model.result("pg1").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.1677796173556252");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.1677796173556252");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.05,45)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"0.20", "0.005", "0.075"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.21365860619221497");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.21365860619221497");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.05,45)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.15, 0.005, 0.1});
    model.component("comp1").geom("geom1").runPre("fin");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.18034688796871434");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.18034688796871434");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.05,45)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.component("comp1").geom("geom1").feature("blk1").active(false);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("cone1", "Cone");
    model.component("comp1").geom("geom1").feature("cone1").set("specifytop", "radius");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("cone1").set("rtop", 0);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("cone1").set("rtop", 0.1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*3.0");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*3.0");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.05,45)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.result("pg2").run();
    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").create("glob1", "Global");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"mbd.rd1.Ip1"});
    model.result().numerical("gev1").set("descr", new String[]{"First principal moment of inertia"});
    model.result().numerical("gev1").set("unit", new String[]{"kg*m^2"});
    model.result().numerical("gev1").setIndex("expr", "mbd.rd1.Ip1", 1);
    model.result().numerical("gev1").setIndex("expr", "mbd.rd1.Ip1", 2);
    model.result().numerical("gev1").setIndex("expr", "mbd.rd1.Ip2", 1);
    model.result().numerical("gev1").setIndex("expr", "mbd.rd1.Ip3", 2);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("Global Evaluation 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.geom()
         .load(new String[]{"part2"}, "Mixer_Module\\Impellers,_Axial\\propeller_constant_pitch.mph", new String[]{"part1"});
    model.geom().remove("part1");
    model.component("comp1").geom("geom1").feature("cone1").active(false);
    model.component("comp1").geom("geom1").run("cone1");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part2");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("mbd").feature("rd1").selection().set(1);

    model.geom().remove("part2");
    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature().remove("cyl1");
    model.component("comp1").geom("geom1").feature().remove("pi1");
    model.component("comp1").geom("geom1").feature("blk1").active(true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("mbd").feature("rd1").selection().set(1);
    model.component("comp1").physics("mbd").feature("rd1").feature("init1").feature("crb1").selection().all();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");

    return model;
  }

  public static Model run4(Model model) {
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.18034688796871434");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.18034688796871434");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.05,45)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.15, 0.005, 0.14});

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.20524375751773793");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.20524375751773793");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.05,45)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").appendResult();
    model.result().table("tbl1").clearTableData();
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"1e-10", "1e-10", "2*pi"});

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.20524375751773793");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.20524375751773793");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.05,45)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.15, 0.005, 0.125});

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.1953202498462461");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.1953202498462461");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.05,45)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.15, 0.005, 0.1});
    model.component("comp1").geom("geom1").run("cone1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 0.01);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 0.01);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new double[]{0.075, 0, 0});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "y");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new double[]{0.075, 0, 0.06});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new double[]{0.075, 0, 0.05});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 0.005);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new double[]{0.075, 0.0025, 0.05});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").feature("swe1").active(false);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.18043350575766132");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.18043350575766132");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.05,45)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").feature("fin").set("action", "union");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("mbd").selection().set();

    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new double[]{0.075, 0.002, 0.05});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("mbd").selection().all();
    model.component("comp1").physics("mbd").selection().set();

    model.component("comp1").geom("geom1").feature().remove("cyl1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("mbd").selection().set(1);

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.18034688796871434");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.18034688796871434");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.05,45)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("glob1").set("expr", new String[]{"mbd.rd1.thx"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"Rigid body rotation, x component"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"rad"});
    model.result("pg3").run();
    model.result("pg3").feature("glob1").setIndex("expr", "mbd.rd1.thx", 1);
    model.result("pg3").feature("glob1").setIndex("expr", "mbd.rd1.thx", 2);
    model.result("pg3").feature("glob1").setIndex("expr", "mbd.rd2.thx", 1);
    model.result("pg3").feature("glob1").setIndex("expr", "mbd.rd3.thx", 2);
    model.result("pg3").feature("glob1").setIndex("expr", "mbd.rd1.thx", 1);
    model.result("pg3").feature("glob1").setIndex("expr", "mbd.rd1.thx", 2);
    model.result("pg3").feature("glob1").setIndex("expr", "mbd.rd1.thy", 1);
    model.result("pg3").feature("glob1").setIndex("expr", "mbd.rd1.thz", 2);
    model.result("pg3").run();
    model.result("pg1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.05,120)");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.18034688796871434");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.18034688796871434");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.05,120)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.15, 0.005, 0.13});
    model.component("comp1").geom("geom1").runPre("fin");

    model.result().table("tbl1").clearTableData();
    model.result().numerical("gev1").set("table", "tbl1");

    return model;
  }

  public static Model run5(Model model) {
    model.result().numerical("gev1").setResult();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.1985572965166478");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.1985572965166478");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.05,120)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.15, 0.005, 0.1});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").runPre("fin");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.18034688796871434");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.18034688796871434");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.05,120)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.15, 0.001, 0.1});
    model.component("comp1").geom("geom1").runPre("fin");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.1802803372528463");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.1802803372528463");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.05,120)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.result("pg1").run();

    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.15, 0.004, 0.1});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"0", "0", "2*pi"});

    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("glob1").set("expr", new String[]{});
    model.result("pg4").feature("glob1").set("descr", new String[]{});
    model.result("pg4").feature("glob1").set("expr", new String[]{"mbd.rd1.xm_effx"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"Effective center of mass, x component"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"m"});
    model.result("pg4").feature("glob1").setIndex("expr", "mbd.rd1.xm_effy", 1);
    model.result("pg4").feature("glob1").setIndex("expr", "mbd.rd1.xm_effx", 2);
    model.result("pg4").feature("glob1").setIndex("expr", "mbd.rd1.xm_effz", 2);
    model.result("pg4").run();
    model.result("pg4").feature("glob1").set("data", "dset1");
    model.result("pg4").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.18032193432857802");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.18032193432857802");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.05,120)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.component("comp1").geom("geom1").run("cone1");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "C:\\temp\\Wing_Nut_M8x1.25.stl");

    model.component().create("mcomp1", "MeshComponent");

    model.geom().create("mgeom1", 3);

    model.mesh().create("mpart1", "mgeom1");

    model.component("comp1").geom("geom1").feature("imp1").set("mesh", "mpart1");

    model.mesh("mpart1").create("imp1", "Import");
    model.mesh("mpart1").feature("imp1").set("filename", "C:\\temp\\Wing_Nut_M8x1.25.stl");

    model.component("comp1").geom("geom1").feature("imp1").set("meshfilename", "");

    model.mesh("mpart1").run();

    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("blk1").active(false);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("mbd").feature("rd1").selection().all();

    model.component("comp1").geom("geom1").run("imp1");
    model.component("comp1").geom("geom1").feature("imp1").set("mesh", "none");
    model.component("comp1").geom("geom1").run("cone1");
    model.component("comp1").geom("geom1").run("cone1");
    model.component("comp1").geom("geom1").feature("imp1").set("meshfilename", "C:\\temp\\Wing_Nut_M8x1.25.stl");

    model.component().create("mcomp2", "MeshComponent");

    model.geom().create("mgeom2", 3);

    model.mesh().create("mpart2", "mgeom2");

    model.component("comp1").geom("geom1").feature("imp1").set("mesh", "mpart2");

    model.mesh("mpart2").create("imp1", "Import");
    model.mesh("mpart2").feature("imp1").set("filename", "C:\\temp\\Wing_Nut_M8x1.25.stl");

    model.component("comp1").geom("geom1").feature("imp1").set("meshfilename", "");

    model.mesh("mpart2").run();

    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").feature("imp1").set("simplifytol", 0.02);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").feature("imp1").set("simplifymesh", false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").run("size");

    model.component("comp1").geom("geom1").feature("imp1").set("simplifymesh", true);
    model.component("comp1").geom("geom1").feature("imp1").set("simplifytol", 0.005);
    model.component("comp1").geom("geom1").feature("imp1").set("defectremoval", 5);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").current("ftet1");

    model.component("comp1").geom("geom1").feature().remove("imp1");
    model.component("comp1").geom("geom1").run("cone1");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "C:\\temp\\M4_x_0.7_Wing_Nut.stl");

    model.component().create("mcomp3", "MeshComponent");

    model.geom().create("mgeom3", 3);

    model.mesh().create("mpart3", "mgeom3");

    model.component("comp1").geom("geom1").feature("imp1").set("mesh", "mpart3");

    model.mesh("mpart3").create("imp1", "Import");
    model.mesh("mpart3").feature("imp1").set("filename", "C:\\temp\\M4_x_0.7_Wing_Nut.stl");

    model.component("comp1").geom("geom1").feature("imp1").set("meshfilename", "");

    model.mesh("mpart3").run();

    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").geom("geom1").feature("imp1").set("formsolid", false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").physics("mbd").selection().all();

    model.component("comp1").geom("geom1").feature("imp1").set("selresult", true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("imp1").set("formsolid", false);
    model.component("comp1").geom("geom1").feature("imp1").set("selindividual", true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("imp1").set("formsolid", true);
    model.component("comp1").geom("geom1").feature("imp1").set("simplifymesh", true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("imp1").set("formsolid", false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").feature("imp1").set("mesh", "mpart3");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("mbd").selection().named("geom1_imp1_1_dom");
    model.component("comp1").physics("mbd").selection().named("geom1_imp1_dom");
    model.component("comp1").physics("mbd").selection().named("geom1_imp1_1_dom");

    model.component("comp1").geom("geom1").feature("imp1").set("formsolid", true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*2.560742193161316");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*2.560742193161316");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.05,120)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.component("comp1").physics("mbd").feature("rd1").set("rho", 7800);

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*2.560742193161316");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*2.560742193161316");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.05,120)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"0", "0", "20*pi"});

    model.study("std1").feature("time").set("tlist", "range(0,0.01,15)");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");

    return model;
  }

  public static Model run6(Model model) {
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*2.560742193161316");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*2.560742193161316");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.01,15)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.study("std1").feature("time").set("tlist", "range(0,0.001,15)");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*2.560742193161316");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*2.560742193161316");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.001,15)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg1");
    model.result().export("anim1").run();
    model.result().export("anim1").set("framesel", "number");
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result().export("anim1").set("showframe", 1);
    model.result().export("anim1").set("maxframes", 1000);
    model.result().export("anim1").run();
    model.result().export("anim1").run();
    model.result().export("anim1").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.0005,2)");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*2.560742193161316");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*2.560742193161316");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.0005,2)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result("pg4").run();
    model.result("pg3").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result().export("anim1").run();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result().export("anim1").run();

    model.component("comp1").geom("geom1").feature().remove("imp1");
    model.component("comp1").geom("geom1").feature().remove("blk1");
    model.component("comp1").geom("geom1").feature().remove("cone1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "1[cm]");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "5[cm]");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("cyl2", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl2").set("axistype", "x");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "7[cm]");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "10[cm]");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"-5[cm]", "0", "0"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.0005,2)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.0005,2)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.result("pg1").run();
    model.result("pg1").feature("surf1").active(false);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").active(true);
    model.result("pg1").feature("surf1").set("expr", "1");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();

    model.study("std1").feature("time").set("plotgroup", "pg1");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.0005,2)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();

    model.component("comp1").physics("mbd").selection().set(1, 3);

    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("cyl1", "cyl2");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.0005,2)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"eta", "eta", "-20*pi"});

    model.param().set("eta", "1e-6[rad/s]");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.0005,2)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.result().table("tbl1").clearTableData();
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.0005,2)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");

    return model;
  }

  public static Model run7(Model model) {
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().export().remove("anim1");
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "12");
    model.result().export("anim1")
         .set("customcolor", new double[]{0.9725490212440491, 0.9725490212440491, 0.9725490212440491});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "off");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "off");
    model.result().export("anim1").set("legend2d", "off");
    model.result().export("anim1").set("logo2d", "off");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "off");
    model.result().export("anim1").set("legend3d", "off");
    model.result().export("anim1").set("logo3d", "off");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "off");
    model.result().export("anim1").set("grid", "off");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "off");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result().export("anim1").set("framesel", "number");
    model.result().export("anim1").set("maxframes", 200);
    model.result().export("anim1").set("showframe", 8);
    model.result().export("anim1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.005,5)");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.005,5)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").run();

    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.0025,8)");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.0025,8)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").run();
    model.result().export("anim1").run();

    model.label("rot2.mph");

    model.result().export("anim1").showFrame();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result().export().create("anim2", "Animation");
    model.result().export("anim2").set("target", "player");
    model.result().export("anim2").set("plotgroup", "pg1");
    model.result().export("anim2").run();
    model.result().export().remove("anim2");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result("pg4").run();
    model.result("pg3").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result().export("anim1").run();
    model.result().export("anim1").set("frametime", 0.3);
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").selection().set(11);
    model.result("pg5").feature("ptgr1").set("expr", "z");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 666, 0);
    model.result("pg1").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result().export("anim1").set("frametime", 0.5);
    model.result("pg4").run();
    model.result("pg3").run();
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr2").set("expr", "x");
    model.result("pg5").run();

    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("pard1", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard1").selection("domain").set("uni1", 3);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature().duplicate("wp2", "wp1");
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "zx");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().move("pard1", 3);
    model.component("comp1").geom("geom1").feature().move("wp1", 2);
    model.component("comp1").geom("geom1").feature().move("wp2", 2);
    model.component("comp1").geom("geom1").feature().move("pard1", 4);
    model.component("comp1").geom("geom1").runPre("pard1");
    model.component("comp1").geom("geom1").feature("pard1").set("workplane", "wp2");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("pard2", "PartitionDomains");
    model.component("comp1").geom("geom1").feature().move("pard2", 5);
    model.component("comp1").geom("geom1").runPre("pard2");
    model.component("comp1").geom("geom1").feature("pard2").selection("domain").set("pard1", 1, 2);
    model.component("comp1").geom("geom1").runPre("fin");

    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature().remove("ptgr2");
    model.result("pg5").run();
    model.result("pg3").run();
    model.result("pg1").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("showframe", 107);
    model.result().export("anim1").run();
    model.result().export("anim1").set("showframe", 108);
    model.result().export("anim1").run();
    model.result().export("anim1").set("showframe", 109);
    model.result().export("anim1").run();
    model.result().export("anim1").set("showframe", 110);
    model.result().export("anim1").run();
    model.result().export("anim1").set("showframe", 111);
    model.result().export("anim1").run();
    model.result().export("anim1").set("showframe", 112);
    model.result().export("anim1").run();
    model.result().export("anim1").set("showframe", 113);
    model.result().export("anim1").run();
    model.result().export("anim1").set("showframe", 88);
    model.result("pg5").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result("pg5").run();
    model.result().export("anim1").showFrame();
    model.result("pg5").run();
    model.result("pg5").feature("ptgr1").set("expr", "w");
    model.result("pg5").run();

    model.component("comp1").geom("geom1").feature().remove("wp2");
    model.component("comp1").geom("geom1").feature().remove("wp1");
    model.component("comp1").geom("geom1").feature().remove("pard1");
    model.component("comp1").geom("geom1").feature().remove("pard2");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "7[cm]", 2);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").run();

    model.result("pg5").run();

    model.component("comp1").geom("geom1").runPre("uni1");
    model.component("comp1").geom("geom1").feature().move("pt1", 2);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").runPre("uni1");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("cyl1", "cyl2", "pt1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.result("pg5").run();

    model.component("comp1").geom("geom1").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.0025,8)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().export("anim1").showFrame();
    model.result("pg5").run();
    model.result("pg5").feature("ptgr1").selection().set(10);
    model.result("pg5").run();

    model.component("comp1").geom("geom1").runPre("uni1");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("cyl1", "cyl2");
    model.component("comp1").geom("geom1").runPre("fin");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.0025,8)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg5").run();
    model.result("pg5").run();

    model.component("comp1").probe().create("pdom1", "DomainPoint");
    model.component("comp1").probe().remove("pdom1");

    model.result().dataset().create("cpt1", "CutPoint2D");
    model.result().dataset().remove("cpt1");
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("pointz", "7[cm]");
    model.result().dataset("cpt1").set("bndsnap", true);
    model.result().dataset("cpt1").set("pointx", 0);
    model.result().dataset("cpt1").set("pointy", 0);
    model.result().dataset("cpt1").set("bndsnap", true);
    model.result("pg5").run();
    model.result("pg5").feature("ptgr1").selection().set();
    model.result("pg5").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.0025,2)");

    model.component("comp1").geom("geom1").feature("pt1").active(false);
    model.component("comp1").geom("geom1").runPre("fin");

    model.result("pg5").run();
    model.result("pg5").feature("ptgr1").selection().set(10);
    model.result("pg5").run();

    model.component("comp1").geom("geom1").feature().remove("pt1");

    model.result("pg5").run();
    model.result("pg5").feature("ptgr1").set("data", "cpt1");
    model.result("pg5").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.0025,2)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "7[cm]", 2);

    model.result().dataset().remove("cpt1");

    return model;
  }

  public static Model run8(Model model) {

    model.component("comp1").geom("geom1").runPre("uni1");
    model.component("comp1").geom("geom1").feature().remove("uni1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("ftet1").active(false);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature().clear();
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run();

    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result().remove("pg4");
    model.result("pg5").run();
    model.result().remove("pg5");
    model.result("pg3").run();
    model.result("pg3").feature().remove("glob1");
    model.result("pg3").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.0025,2)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runFromTo("st1", "v1");

    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").selection().set(10);
    model.result("pg3").feature("ptgr1").set("expr", "w");
    model.result("pg3").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.0025,2)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("ptgr2", "PointGraph");
    model.result("pg3").feature("ptgr2").selection().set(10);
    model.result("pg3").feature("ptgr2").set("expr", "u");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("ptgr1").set("expr", "u");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr2").set("expr", "v");
    model.result("pg3").run();
    model.result("pg3").create("ptgr3", "PointGraph");
    model.result("pg3").feature("ptgr3").set("expr", "w");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr3").selection().set(10);
    model.result("pg3").run();
    model.result("pg3").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.0025,8)");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.0025,8)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg3").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").run();
    model.result().export("anim1").set("frametime", 0.25);

    model.component().remove("mcomp1");
    model.component().remove("mcomp2");
    model.component().remove("mcomp3");

    model.result("pg3").run();
    model.result("pg3").label("Tip displacements");

    model.label("rot2_revised.mph");

    model.result("pg3").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("ptgr2").active(false);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("ptgr1").active(false);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("ptgr2").active(true);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("ptgr1").active(true);
    model.result("pg3").run();
    model.result("pg3").feature("ptgr1").active(false);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("ptgr2").active(false);
    model.result("pg3").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result("pg3").run();

    model.component("comp1").physics("mbd").feature("rd1").set("rho_mat", "from_mat");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup().create("Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").propertyGroup().create("Lame", "Lam\u00e9 parameters");
    model.component("comp1").material("mat1").label("Aluminum");
    model.component("comp1").material("mat1").set("family", "aluminum");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").descr("relpermeability_symmetry", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def").descr("heatcapacity_symmetry", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").descr("thermalconductivity_symmetry", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").descr("electricconductivity_symmetry", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").descr("relpermittivity_symmetry", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").descr("thermalexpansioncoefficient_symmetry", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def").descr("density_symmetry", "");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("youngsmodulus", "70e9[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").descr("youngsmodulus_symmetry", "");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("poissonsratio", "0.33");
    model.component("comp1").material("mat1").propertyGroup("Enu").descr("poissonsratio_symmetry", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-2.5e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-3.3e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-3.5e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").descr("l_symmetry", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").descr("m_symmetry", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").descr("n_symmetry", "");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("lambLame", "");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("muLame", "");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("lambLame", "");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("muLame", "");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("lambLame", "5.1e10[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("muLame", "2.6e10[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Lame").descr("lambLame_symmetry", "");
    model.component("comp1").material("mat1").propertyGroup("Lame").descr("muLame_symmetry", "");
    model.component("comp1").material("mat1").set("groups", new String[][]{});
    model.component("comp1").material("mat1").set("family", "aluminum");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.0025,8)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("ptgr2").active(true);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("ptgr3").active(false);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("ptgr2").active(false);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("ptgr3").active(true);
    model.result("pg3").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result("pg3").run();
    model.result("pg1").run();
    model.result("pg3").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result().export("anim1").set("showframe", 29);
    model.result().export("anim1").run();
    model.result().export("anim1").set("showframe", 28);
    model.result().export("anim1").run();
    model.result().export("anim1").set("showframe", 27);
    model.result().export("anim1").run();
    model.result().export("anim1").set("showframe", 26);
    model.result().export("anim1").run();
    model.result().export("anim1").set("showframe", 25);
    model.result().export("anim1").run();
    model.result().export("anim1").set("showframe", 24);
    model.result().export("anim1").run();
    model.result().export("anim1").set("showframe", 23);
    model.result().export("anim1").run();
    model.result().export("anim1").set("showframe", 22);
    model.result().export("anim1").run();
    model.result().export("anim1").set("showframe", 21);
    model.result().export("anim1").run();
    model.result().export("anim1").set("showframe", 20);
    model.result().export("anim1").run();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg1");
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result("pg3").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("ptgr2").active(true);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("ptgr3").active(false);
    model.result("pg3").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.0025,8)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    return model;
  }

  public static Model run9(Model model) {

    model.study("std1").feature("time").set("tlist", "range(0,0.015,2.7)");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.7)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.result("pg3").run();
    model.result("pg3").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.7)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("ptgr3").active(true);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("ptgr2").active(false);
    model.result("pg3").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.015,2.1)");

    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();

    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1")
         .set("filename", "C:\\Users\\dixita\\Downloads\\racket.stl");

    model.component().create("mcomp1", "MeshComponent");

    model.geom().create("mgeom1", 3);

    model.mesh().create("mpart1", "mgeom1");

    model.component("comp1").geom("geom1").feature("imp1").set("mesh", "mpart1");

    model.mesh("mpart1").create("imp1", "Import");
    model.mesh("mpart1").feature("imp1").set("filename", "C:\\Users\\dixita\\Downloads\\racket.stl");

    model.component("comp1").geom("geom1").feature("imp1").set("meshfilename", "");

    model.mesh("mpart1").run();

    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("imp1").active(false);
    model.component("comp1").geom("geom1").feature("imp1").label("Tennis Racket");
    model.component("comp1").geom("geom1").run("imp1");
    model.component("comp1").geom("geom1").create("imp2", "Import");
    model.component("comp1").geom("geom1").run("imp1");
    model.component("comp1").geom("geom1").feature("imp2").set("type", "native");
    model.component("comp1").geom("geom1").feature().remove("imp1");
    model.component("comp1").geom("geom1").feature().remove("imp2");
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1")
         .set("filename", "C:\\Users\\dixita\\Downloads\\racket.stl");

    model.component().create("mcomp2", "MeshComponent");

    model.geom().create("mgeom2", 3);

    model.mesh().create("mpart2", "mgeom2");

    model.component("comp1").geom("geom1").feature("imp1").set("mesh", "mpart2");

    model.mesh("mpart2").create("imp1", "Import");
    model.mesh("mpart2").feature("imp1").set("filename", "C:\\Users\\dixita\\Downloads\\racket.stl");

    model.component("comp1").geom("geom1").feature("imp1").set("meshfilename", "");

    model.mesh("mpart2").run();

    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("imp1").label("Tennis Racket");
    model.component("comp1").geom("geom1").feature("imp1").active(false);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("imp1");
    model.component("comp1").geom("geom1").create("imp2", "Import");
    model.component("comp1").geom("geom1").feature("imp2")
         .set("filename", "C:\\Users\\dixita\\Downloads\\pifa_handheld.mphbin");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("imp2").active(false);
    model.component("comp1").geom("geom1").feature("imp2").label("Cellphone");
    model.component("comp1").geom("geom1").run("fin");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").feature("imp1").active(true);
    model.component("comp1").geom("geom1").feature("imp2").active(true);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("imp1").active(false);
    model.component("comp1").geom("geom1").feature("imp2").active(false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("transparency", false);

    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("mbd").feature("rd1").feature("init1").set("Omega", new int[]{1, 0, 0});
    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"-20*pi", "eta", "eta"});

    model.component("comp1").geom("geom1").feature("imp1").active(true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("imp1").active(false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.label("rot2_revised_app.mph");

    model.result().export("anim1").set("showframe", 200);
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").set("frametime", 0.05);

    model.study("std1").feature("time").set("tlist", "range(0,0.015,2.7)");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.7)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").set("frametime", 0.25);
    model.result().export("anim1").set("repeat", true);
    model.result().export("anim1").set("framesel", "number");
    model.result().export("anim1").set("solnumtype", "level1");
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.015,2.1)");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().export("anim1").showFrame();

    model.component("comp1").geom("geom1").feature("pt1").active(false);
    model.component("comp1").geom("geom1").feature("cyl2").active(false);
    model.component("comp1").geom("geom1").feature("cyl1").active(false);

    model.result().export("anim1").showFrame();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();

    model.component("comp1").geom("geom1").run();

    model.result().export("anim1").showFrame();
    model.result().export().remove("anim1");
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "12");
    model.result().export("anim1")
         .set("customcolor", new double[]{0.9725490212440491, 0.9725490212440491, 0.9725490212440491});
    model.result().export("anim1").set("background", "current");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "off");
    model.result().export("anim1").set("logo1d", "off");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "off");
    model.result().export("anim1").set("options2d", "on");
    model.result().export("anim1").set("title3d", "off");
    model.result().export("anim1").set("legend3d", "off");
    model.result().export("anim1").set("logo3d", "off");
    model.result().export("anim1").set("options3d", "on");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "off");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("solnumtype", "inner");
    model.result().export().remove("anim1");
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("fontsize", "12");
    model.result().export("anim1")
         .set("customcolor", new double[]{0.9725490212440491, 0.9725490212440491, 0.9725490212440491});
    model.result().export("anim1").set("background", "current");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "off");
    model.result().export("anim1").set("logo1d", "off");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "off");
    model.result().export("anim1").set("options2d", "on");
    model.result().export("anim1").set("title3d", "off");
    model.result().export("anim1").set("legend3d", "off");
    model.result().export("anim1").set("logo3d", "off");
    model.result().export("anim1").set("options3d", "on");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "off");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export().remove("anim1");
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "12");
    model.result().export("anim1")
         .set("customcolor", new double[]{0.9725490212440491, 0.9725490212440491, 0.9725490212440491});
    model.result().export("anim1").set("background", "current");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "off");
    model.result().export("anim1").set("logo1d", "off");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "off");
    model.result().export("anim1").set("options2d", "on");
    model.result().export("anim1").set("title3d", "off");
    model.result().export("anim1").set("legend3d", "off");
    model.result().export("anim1").set("logo3d", "off");
    model.result().export("anim1").set("options3d", "on");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "off");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("solnumtype", "inner");
    model.result().export("anim1").run();

    model.label("rot2_revised_app.mph");

    model.result().export("anim1").showFrame();
    model.result("pg1").run();

    model.component("comp1").geom("geom1").feature().remove("imp1");
    model.component("comp1").geom("geom1")
         .insertFile("\\\\bos-filer1.comsol.com\\Users$\\andrew\\Desktop\\spinning_tennis_racket.mph", "geom1");
    model.component("comp1").geom("geom1").feature().createAfter("if1", "If", "imp2");
    model.component("comp1").geom("geom1").feature().remove("if1");

    return model;
  }

  public static Model run10(Model model) {
    model.component("comp1").geom("geom1").run("del2");
    model.component("comp1").geom("geom1").create("if1", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature().move("if1", 4);
    model.component("comp1").geom("geom1").feature("if1").active(false);
    model.component("comp1").geom("geom1").feature("endif1").active(false);
    model.component("comp1").geom("geom1").run("fin");

    model.param().set("racket_geom", "1");

    model.component("comp1").geom("geom1").feature("if1").set("condition", "racket_geom==1");
    model.component("comp1").geom("geom1").feature("if1").active(true);
    model.component("comp1").geom("geom1").feature("endif1").active(true);
    model.component("comp1").geom("geom1").runPre("fin");

    model.param().set("racket_geom", "0");

    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").feature("cyl1").active(true);
    model.component("comp1").geom("geom1").feature("cyl2").active(true);
    model.component("comp1").geom("geom1").feature("pt1").active(true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("imp2");
    model.component("comp1").geom("geom1").feature("imp2").active(true);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").feature("imp2").active(false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("cyl1").active(false);
    model.component("comp1").geom("geom1").feature("cyl2").active(false);
    model.component("comp1").geom("geom1").feature("pt1").active(false);

    model.param().set("racket_geom", "1");

    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").run();
    model.component("comp1").mesh("mesh2").automatic(false);
    model.component("comp1").mesh("mesh2").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh2").feature("ftri1").selection().set(33, 34);

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").mesh("mesh2").run("ftri1");
    model.component("comp1").mesh("mesh2").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh2").feature("swe1").selection("sourceface").set(33, 34);
    model.component("comp1").mesh("mesh2").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh2").feature("swe1").selection().set(11);
    model.component("comp1").mesh("mesh2").run("swe1");
    model.component("comp1").mesh("mesh2").feature("swe1").selection().set(1, 3, 11, 12);
    model.component("comp1").mesh("mesh2").run("swe1");
    model.component("comp1").mesh("mesh2").feature("swe1").selection().set(1, 2, 3, 4, 11, 12, 13, 14);
    model.component("comp1").mesh("mesh2").run("swe1");
    model.component("comp1").mesh("mesh2").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh2").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh2").feature("ftet1").selection().set(5, 6, 15, 16);
    model.component("comp1").mesh("mesh2").run("ftet1");
    model.component("comp1").mesh("mesh2").create("ftet2", "FreeTet");
    model.component("comp1").mesh("mesh2").feature("ftet2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh2").feature("ftet2").selection().set(7, 8, 10);
    model.component("comp1").mesh("mesh2").run("ftet2");
    model.component("comp1").mesh("mesh2").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh2").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh2").feature("swe2").selection().set(9);
    model.component("comp1").mesh("mesh2").feature("swe2").selection("sourceface").set(95);
    model.component("comp1").mesh("mesh2").run("swe2");
    model.component("comp1").mesh("mesh2").run();
    model.component("comp1").mesh("mesh2").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh2").feature("ftri2").selection().remaining();
    model.component("comp1").mesh("mesh2").run();

    model.study("std1").feature("time").setIndex("mesh", "mesh2", 1);
    model.study("std1").feature("time").setIndex("mesh", "mesh1", 1);
    model.study("std1").feature("time").setIndex("mesh", "mesh2", 1);
    model.study("std1").feature("time").setIndex("mesh", "mesh1", 1);

    model.component("comp1").view("view1").set("transparency", false);

    model.study("std1").feature("time").setIndex("mesh", "mesh2", 1);

    model.component("comp1").mesh("mesh2").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"2*pi", "eta", "eta"});

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").geom("geom1").runPre("del2");
    model.component("comp1").geom("geom1").feature("del2").selection("input").clear("del1(1)");

    model.result("pg1").run();

    model.component("comp1").geom("geom1").run();

    model.component("comp1").view("view1").set("transparency", false);
    model.component("comp1").view("view1").set("renderwireframe", false);
    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").geom("geom1").runPre("del2");
    model.component("comp1").geom("geom1").feature("del2").selection("input").set("del1(2)", 24, 25, 26, 34);

    model.component("comp1").view("view1").set("transparency", false);

    model.component("comp1").geom("geom1").run("del2");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").geom("geom1").run("fin");

    model.result("pg1").run();

    model.component("comp1").geom("geom1").runPre("del2");
    model.component("comp1").geom("geom1").feature("del2").selection("input").set("del1(1)", 16, 20);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("transparency", false);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh2").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").geom("geom1").runPre("del2");
    model.component("comp1").geom("geom1").feature("del2").selection("input").set("swe3", 2, 11, 15, 19);
    model.component("comp1").geom("geom1").feature("del2").selection("input").set("del1(2)", 24, 25, 26, 29, 34);
    model.component("comp1").geom("geom1").feature("del2").selection("input").set("swe3", 2, 11, 15, 19, 23, 27);
    model.component("comp1").geom("geom1").feature("del2").selection("input")
         .set("del1(2)", 24, 25, 26, 28, 29, 30, 31, 32, 33, 34);
    model.component("comp1").geom("geom1").runPre("del1");
    model.component("comp1").geom("geom1").runPre("del2");
    model.component("comp1").geom("geom1").feature("del2").selection("input").set("del1(2)", 24, 34);
    model.component("comp1").geom("geom1").feature("del2").selection("input").set("del1(1)", 16);
    model.component("comp1").geom("geom1").feature("del2").selection("input").set("swe3", 19, 23, 27);
    model.component("comp1").geom("geom1").feature("del2").selection("input").clear("swe3");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh2").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("edges", false);

    model.component("comp1").physics("mbd").selection().all();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.773475924425763");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.773475924425763");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.component("comp1").view("view1").set("transparency", false);

    model.result("pg1").run();
    model.result("pg1").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").run();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"4*pi", "eta", "eta"});

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.773475924425763");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.773475924425763");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("repeat", true);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"-20*pi", "eta", "eta"});

    model.study("std1").feature("time").setIndex("mesh", "mesh1", 1);

    model.component("comp1").geom("geom1").feature("cyl1").active(true);
    model.component("comp1").geom("geom1").feature("cyl2").active(true);
    model.component("comp1").geom("geom1").feature("pt1").active(true);

    model.param().set("racket_geom", "0");

    model.component("comp1").geom("geom1").run("fin");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").automatic(true);
    model.component("comp1").mesh("mesh1").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);

    return model;
  }

  public static Model run11(Model model) {
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.component("comp1").geom("geom1").feature("cyl1").active(false);
    model.component("comp1").geom("geom1").feature("cyl2").active(false);
    model.component("comp1").geom("geom1").feature("pt1").active(false);

    model.param().set("racket_geom", "1");

    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").mesh("mesh2").feature("ftri1").selection().set(115, 117);
    model.component("comp1").mesh("mesh2").feature("swe1").selection().set(1, 2, 3, 4, 11, 12, 13, 14);
    model.component("comp1").mesh("mesh2").feature("swe1").selection("sourceface").set(115, 117);
    model.component("comp1").mesh("mesh2").feature("ftet1").selection().set(5, 6, 15, 16);
    model.component("comp1").mesh("mesh2").feature("ftet2").selection().set(7, 8, 10);
    model.component("comp1").mesh("mesh2").feature("swe2").selection().set(9);
    model.component("comp1").mesh("mesh2").feature("swe2").selection("sourceface").set(95);
    model.component("comp1").mesh("mesh2").run();

    model.param().set("racket_geom", "0");

    model.component("comp1").geom("geom1").feature("cyl1").active(true);
    model.component("comp1").geom("geom1").feature("cyl2").active(true);
    model.component("comp1").geom("geom1").feature("pt1").active(true);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("transparency", false);

    model.label("rot2_revised_app_AG.mph");

    model.result().export("anim1").set("target", "player");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").set("solnumtype", "level1");
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result("pg1").run();

    model.label("rot2_revised_app_AG.mph");

    model.component("comp1").geom("geom1").feature("cyl1").active(false);
    model.component("comp1").geom("geom1").feature("cyl2").active(false);
    model.component("comp1").geom("geom1").feature("pt1").active(false);
    model.component("comp1").geom("geom1")
         .insertFile("\\\\bos-filer1.comsol.com\\Users$\\dixita\\Desktop\\cellphone.mph", "geom2");
    model.component("comp1").geom("geom1").feature("wp10").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp10").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp10").geom().run("fil1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("if2", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif2", "EndIf", "if2");
    model.component("comp1").geom("geom1").feature().move("if2", 28);

    model.param().set("cellphone_geom", "0");

    model.component("comp1").geom("geom1").feature("if2").set("condition", "cellphone_geom==1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().remove("imp2");

    model.param().set("cellphone_geom", "1");

    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"4*pi", "eta", "eta"});

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.param().set("cellphone_geom", "0");

    model.component("comp1").geom("geom1").feature("cyl1").active(true);
    model.component("comp1").geom("geom1").feature("pt1").active(true);
    model.component("comp1").geom("geom1").feature("cyl2").active(true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.param().set("racket_geom", "1");

    model.component("comp1").geom("geom1").feature("cyl2").active(false);
    model.component("comp1").geom("geom1").feature("pt1").active(false);
    model.component("comp1").geom("geom1").feature("cyl1").active(false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("endif2");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.param().set("racket_geom", "0");

    model.component("comp1").geom("geom1").feature("cyl1").active(true);
    model.component("comp1").geom("geom1").feature("cyl2").active(true);
    model.component("comp1").geom("geom1").feature("pt1").active(true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").feature("cyl1").active(false);
    model.component("comp1").geom("geom1").feature("cyl2").active(false);
    model.component("comp1").geom("geom1").feature("pt1").active(false);

    model.param().set("cellphone_geom", "1");

    model.component("comp1").geom("geom1").runPre("fin");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature("surf2").set("expr", "1");
    model.result("pg1").feature("surf2").set("coloring", "uniform");
    model.result("pg1").feature("surf2").set("color", "black");
    model.result("pg1").feature("surf2").create("sel1", "Selection");
    model.result("pg1").feature("surf2").feature("sel1").selection().set(30);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf2").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("inheritplot", "surf1");
    model.result("pg1").feature("surf2").set("inheritcolor", false);
    model.result("pg1").feature("surf2").set("inheritheightscale", false);
    model.result("pg1").feature("surf2").set("inheritrange", false);
    model.result("pg1").run();
    model.result("pg1").feature().remove("surf2");
    model.result("pg1").run();

    model.param().set("cellphone_geom", "0");

    model.component("comp1").geom("geom1").feature("cyl1").active(true);
    model.component("comp1").geom("geom1").feature("wp10").active(false);
    model.component("comp1").geom("geom1").feature("cyl2").active(true);
    model.component("comp1").geom("geom1").feature("pt1").active(true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").runPre("ext1");

    model.param().set("cellphone_geom", "1");

    model.component("comp1").geom("geom1").run("wp10");
    model.component("comp1").geom("geom1").run("wp10");
    model.component("comp1").geom("geom1").run("wp10");
    model.component("comp1").geom("geom1").feature("wp10").active(true);
    model.component("comp1").geom("geom1").runPre("fin");

    model.param().set("cellphone_geom", "0");

    model.component("comp1").geom("geom1").run("fin");

    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg2").run();
    model.result("pg1").run();
    model.result("pg3").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").feature("ptgr3").selection().set(17);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();

    model.param().set("cellphone_geom", "1");

    model.component("comp1").geom("geom1").feature("cyl1").active(false);
    model.component("comp1").geom("geom1").feature("cyl2").active(false);
    model.component("comp1").geom("geom1").feature("pt1").active(false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"-10*pi", "eta", "eta"});

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"-20*pi", "eta", "eta"});

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");

    return model;
  }

  public static Model run12(Model model) {
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"10*pi", "eta", "eta"});

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"30*pi", "eta", "eta"});

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"-5*pi", "eta", "eta"});

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"-10*pi", "eta", "eta"});

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.param().set("cellphone_geom", "0");

    model.component("comp1").geom("geom1").feature("cyl1").active(true);
    model.component("comp1").geom("geom1").feature("cyl2").active(true);
    model.component("comp1").geom("geom1").feature("pt1").active(true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.param().set("racket_geom", "1");

    model.component("comp1").geom("geom1").feature("cyl1").active(false);
    model.component("comp1").geom("geom1").feature("cyl2").active(false);
    model.component("comp1").geom("geom1").feature("pt1").active(false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"4*pi", "eta", "eta"});

    model.sol("sol1").study("std1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").attach("std1");

    model.result().export("anim1").showFrame();
    model.result("pg1").run();

    model.sol("sol1").study("std1");
    model.sol("sol1").attach("std1");

    model.component("comp1").mesh("mesh2").run();

    model.result("pg3").run();
    model.result("pg3").run();

    model.sol("sol1").study("std1");
    model.sol("sol1").attach("std1");

    model.param().set("racket_geom", "0");
    model.param().set("cellphone_geom", "0");
    model.param().set("racket_geom", "1");

    model.component("comp1").mesh("mesh2").run();
    model.component("comp1").mesh("mesh1").clearMesh();
    model.component("comp1").mesh("mesh2").run();
    model.component("comp1").mesh("mesh2").run();

    model.sol("sol1").study("std1");
    model.sol("sol1").attach("std1");

    model.component("comp1").mesh("mesh1").clearMesh();
    model.component("comp1").mesh("mesh2").run();

    model.study("std1").feature("time").setIndex("mesh", "mesh2", 1);

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.7734759244257629");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.7734759244257629");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

//    Started to run method mesh_racket

    model.component("comp1").mesh("mesh2").feature("ftri1").selection().set(115, 117);
    model.component("comp1").mesh("mesh2").feature("swe1").selection().set(1, 2, 3, 4, 11, 12, 13, 14);
    model.component("comp1").mesh("mesh2").feature("swe1").selection("sourceface").set(115, 117);
    model.component("comp1").mesh("mesh2").feature("ftet1").selection().set(5, 6, 15, 16);
    model.component("comp1").mesh("mesh2").feature("ftet2").selection().set(7, 8, 10);
    model.component("comp1").mesh("mesh2").feature("swe2").selection().set(9);
    model.component("comp1").mesh("mesh2").feature("swe2").selection("sourceface").set(95);
    model.component("comp1").mesh("mesh2").run();

//    Finished running method mesh_racket

    model.component("comp1").mesh("mesh2").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.7734759244257629");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.7734759244257629");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature("surf2").set("expr", "1");
    model.result("pg1").feature("surf2").set("coloring", "uniform");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result("pg1").feature("surf2").create("sel1", "Selection");
    model.result("pg1").feature("surf2").feature("sel1").selection()
         .set(4, 5, 8, 9, 12, 13, 19, 20, 23, 24, 27, 28, 39, 40, 43, 44, 47, 48, 51, 52, 54, 57, 59, 62, 64, 71, 73, 76, 78, 80, 82, 84, 89, 91, 94, 98, 100, 102, 104, 106, 108, 116, 118, 121, 123, 125, 126, 127, 128, 129, 130, 131, 132, 153, 155, 157, 161, 163, 165, 169, 171, 173, 177, 179, 181);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 6, 7, 10, 11, 14, 15, 16, 17, 18, 21, 22, 25, 26, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 41, 42, 45, 46, 49, 50, 53, 55, 56, 58, 60, 61, 63, 65, 66, 67, 68, 69, 70, 72, 74, 75, 77, 79, 81, 83, 85, 86, 87, 88, 90, 92, 93, 95, 96, 97, 99, 101, 103, 105, 107, 109, 110, 111, 112, 113, 114, 115, 117, 119, 120, 122, 124, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 154, 156, 158, 159, 160, 162, 164, 166, 167, 168, 170, 172, 174, 175, 176, 178, 180, 182);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 6, 7, 10, 11, 14, 15, 16, 17, 18, 21, 22, 25, 26, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 41, 42, 45, 46, 49, 50, 53, 55, 56, 58, 60, 61, 63, 65, 66, 67, 68, 69, 70, 72, 74, 75, 77, 79, 81, 83, 85, 86, 87, 88, 89, 90, 92, 93, 94, 95, 96, 97, 99, 101, 103, 105, 107, 109, 110, 111, 112, 113, 114, 115, 117, 119, 120, 122, 124, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 154, 156, 158, 159, 160, 162, 164, 166, 167, 168, 170, 172, 174, 175, 176, 178, 180, 182);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("sel1").selection()
         .set(4, 5, 8, 9, 12, 13, 19, 20, 23, 24, 27, 28, 39, 40, 43, 44, 47, 48, 51, 52, 54, 57, 59, 62, 64, 71, 73, 76, 78, 80, 82, 84, 91, 98, 100, 102, 104, 106, 108, 116, 118, 121, 123, 125, 126, 127, 128, 129, 130, 131, 132, 153, 155, 157, 161, 163, 165, 169, 171, 173, 177, 179, 181);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("color", "custom");
    model.result("pg1").feature("surf2")
         .set("customcolor", new double[]{0.21176470816135406, 0.5490196347236633, 0.7960784435272217});
    model.result("pg1").run();
    model.result("pg1").create("surf3", "Surface");
    model.result("pg1").feature("surf3").set("expr", "1");
    model.result("pg1").feature("surf3").set("coloring", "uniform");
    model.result("pg1").feature("surf3").set("color", "custom");
    model.result("pg1").feature("surf3").create("sel1", "Selection");

    return model;
  }

  public static Model run13(Model model) {
    model.result("pg1").feature("surf3").feature("sel1").selection()
         .set(6, 7, 10, 11, 14, 15, 21, 22, 25, 26, 29, 30, 41, 42, 45, 46, 49, 50, 55, 56, 58, 60, 63, 65, 72, 74, 77, 79, 81, 83, 85, 97, 99, 101, 103, 105, 107, 109, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 154, 156, 158, 162, 164, 166, 170, 172, 174, 175, 178, 180, 182);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 6, 7, 16, 17, 18, 31, 32, 33, 34, 35, 36, 37, 38, 53, 61, 66, 67, 68, 69, 70, 75, 86, 87, 88, 89, 90, 92, 93, 94, 95, 96, 110, 111, 112, 113, 114, 115, 117, 119, 120, 122, 124, 133, 134, 135, 136, 149, 150, 151, 152, 159, 160, 167, 168, 176);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf3")
         .set("customcolor", new double[]{0.7490196228027344, 0.1411764770746231, 0.3686274588108063});
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf2").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("def1").set("scaleactive", true);
    model.result("pg1").feature("surf2").feature("def1").set("scale", 1);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf3").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("surf3").feature("def1").set("scaleactive", true);
    model.result("pg1").feature("surf3").feature("def1").set("scale", 1);
    model.result("pg1").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result("pg1").run();
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").activate("mbd", true);
    model.study("std2").feature("time").set("tlist", "range(0,0.015,2.1)");
    model.study("std2").feature("time").setIndex("mesh", "mesh1", 1);

    model.param().set("racket_geom", "0");

    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").feature("cyl1").active(true);
    model.component("comp1").geom("geom1").feature("cyl2").active(true);
    model.component("comp1").geom("geom1").feature("pt1").active(true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"eta", "eta", "-20*pi"});
    model.component("comp1").physics("mbd").feature("rd1").feature("init1").set("Omega", new int[]{0, 0, 1});

    model.component("comp1").mesh("mesh1").run();

    model.sol().create("sol2");
    model.sol("sol2").study("std2");

    model.study("std2").feature("time").set("notlistsolnum", 1);
    model.study("std2").feature("time").set("notsolnum", "1");
    model.study("std2").feature("time").set("listsolnum", 1);
    model.study("std2").feature("time").set("solnum", "1");

    model.sol("sol2").create("st1", "StudyStep");
    model.sol("sol2").feature("st1").set("study", "std2");
    model.sol("sol2").feature("st1").set("studystep", "time");
    model.sol("sol2").create("v1", "Variables");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol2").feature("v1").set("control", "time");
    model.sol("sol2").create("t1", "Time");
    model.sol("sol2").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol2").feature("t1").set("plot", "off");
    model.sol("sol2").feature("t1").set("plotgroup", "pg1");
    model.sol("sol2").feature("t1").set("plotfreq", "tout");
    model.sol("sol2").feature("t1").set("probesel", "all");
    model.sol("sol2").feature("t1").set("probes", new String[]{});
    model.sol("sol2").feature("t1").set("probefreq", "tsteps");
    model.sol("sol2").feature("t1").set("rtol", 0.01);
    model.sol("sol2").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol2").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol2").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol2").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol2").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol2").feature("t1").set("maxorder", 2);
    model.sol("sol2").feature("t1").set("minorder", 1);
    model.sol("sol2").feature("t1").set("control", "time");
    model.sol("sol2").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol2").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol2").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol2").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol2").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol2").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol2").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol2").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol2").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol2").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol2").feature("t1").feature().remove("fcDef");
    model.sol("sol2").attach("std2");

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("Displacement (mbd) 1");
    model.result("pg4").set("showlooplevel", new String[]{"off", "off", "off"});
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("Surface");
    model.result("pg4").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature("surf1").feature().create("def1", "Deform");
    model.result("pg4").feature("surf1").feature("def1").label("Deformation");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("Velocity (mbd)");
    model.result("pg5").set("showlooplevel", new String[]{"off", "off", "off"});
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").feature().create("vol1", "Volume");
    model.result("pg5").feature("vol1").label("Volume");
    model.result("pg5").feature("vol1").set("expr", "mod(dom,10)");
    model.result("pg5").feature("vol1").set("unit", "1");
    model.result("pg5").feature("vol1").set("colortable", "Cyclic");
    model.result("pg5").feature("vol1").set("colorlegend", false);
    model.result("pg5").feature("vol1").set("data", "parent");
    model.result("pg5").feature("vol1").feature().create("def1", "Deform");
    model.result("pg5").feature("vol1").feature("def1").label("Deformation");
    model.result("pg5").feature().create("arwl1", "ArrowLine");
    model.result("pg5").feature("arwl1").label("Arrow Line");
    model.result("pg5").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY", "mbd.u_tZ"});
    model.result("pg5").feature("arwl1").set("placement", "elements");
    model.result("pg5").feature("arwl1").set("data", "parent");
    model.result("pg5").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg5").feature("arwl1").feature("def1").label("Deformation");

    model.sol("sol2").runAll();

    model.result("pg4").run();
    model.result("pg4").set("data", "dset2");
    model.result("pg5").set("data", "dset2");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "custom");
    model.result("pg4").feature("surf1").set("wireframe", true);
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "gray");
    model.result("pg4").feature("surf1").set("wireframe", false);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").set("edges", false);
    model.result("pg4").run();

    model.param().set("cellphone_geom", "1");

    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("cyl1").active(false);
    model.component("comp1").geom("geom1").feature("cyl2").active(false);
    model.component("comp1").geom("geom1").feature("pt1").active(false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("mbd").feature("rd1").feature("init1").set("Omega", new int[]{1, 0, 0});
    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"-10*pi", "eta", "eta"});

    model.component("comp1").mesh("mesh1").run();

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").activate("mbd", true);
    model.study("std3").feature("time").setIndex("mesh", "mesh1", 1);
    model.study("std3").feature("time").set("tlist", "range(0,0.015,2.1)");

    model.sol().create("sol3");
    model.sol("sol3").study("std3");

    model.study("std3").feature("time").set("notlistsolnum", 1);
    model.study("std3").feature("time").set("notsolnum", "1");
    model.study("std3").feature("time").set("listsolnum", 1);
    model.study("std3").feature("time").set("solnum", "1");

    model.sol("sol3").create("st1", "StudyStep");
    model.sol("sol3").feature("st1").set("study", "std3");
    model.sol("sol3").feature("st1").set("studystep", "time");
    model.sol("sol3").create("v1", "Variables");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol3").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol3").feature("v1").set("control", "time");
    model.sol("sol3").create("t1", "Time");
    model.sol("sol3").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol3").feature("t1").set("plot", "off");
    model.sol("sol3").feature("t1").set("plotgroup", "pg1");
    model.sol("sol3").feature("t1").set("plotfreq", "tout");
    model.sol("sol3").feature("t1").set("probesel", "all");
    model.sol("sol3").feature("t1").set("probes", new String[]{});
    model.sol("sol3").feature("t1").set("probefreq", "tsteps");
    model.sol("sol3").feature("t1").set("rtol", 0.01);
    model.sol("sol3").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol3").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol3").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol3").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol3").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol3").feature("t1").set("maxorder", 2);
    model.sol("sol3").feature("t1").set("minorder", 1);
    model.sol("sol3").feature("t1").set("control", "time");
    model.sol("sol3").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol3").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol3").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol3").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol3").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol3").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol3").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol3").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol3").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol3").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol3").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol3").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol3").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol3").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol3").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol3").feature("t1").feature().remove("fcDef");
    model.sol("sol3").attach("std3");

    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("Displacement (mbd) 2");
    model.result("pg6").set("showlooplevel", new String[]{"off", "off", "off"});
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("Surface");
    model.result("pg6").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").feature("surf1").feature().create("def1", "Deform");
    model.result("pg6").feature("surf1").feature("def1").label("Deformation");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("Velocity (mbd) 1");
    model.result("pg7").set("showlooplevel", new String[]{"off", "off", "off"});
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").feature().create("vol1", "Volume");
    model.result("pg7").feature("vol1").label("Volume");
    model.result("pg7").feature("vol1").set("expr", "mod(dom,10)");
    model.result("pg7").feature("vol1").set("unit", "1");
    model.result("pg7").feature("vol1").set("colortable", "Cyclic");
    model.result("pg7").feature("vol1").set("colorlegend", false);
    model.result("pg7").feature("vol1").set("data", "parent");
    model.result("pg7").feature("vol1").feature().create("def1", "Deform");
    model.result("pg7").feature("vol1").feature("def1").label("Deformation");
    model.result("pg7").feature().create("arwl1", "ArrowLine");
    model.result("pg7").feature("arwl1").label("Arrow Line");
    model.result("pg7").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY", "mbd.u_tZ"});
    model.result("pg7").feature("arwl1").set("placement", "elements");
    model.result("pg7").feature("arwl1").set("data", "parent");
    model.result("pg7").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg7").feature("arwl1").feature("def1").label("Deformation");

    model.sol("sol3").runAll();

    model.result("pg6").run();
    model.result("pg6").set("data", "dset3");
    model.result("pg7").set("data", "dset3");
    model.result("pg6").run();
    model.result("pg5").run();
    model.result().remove("pg5");
    model.result("pg1").run();
    model.result("pg1").label("Tennis Racket");
    model.result("pg4").run();
    model.result("pg4").label("T-Handle");
    model.result("pg6").run();
    model.result("pg4").run();
    model.result("pg7").run();
    model.result().remove("pg7");
    model.result().export("anim1").showFrame();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").label("Cellphone");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("expr", "1");
    model.result("pg6").feature("surf1").set("coloring", "uniform");
    model.result("pg6").feature("surf1").set("color", "gray");
    model.result("pg6").run();
    model.result("pg6").set("edges", false);
    model.result("pg6").run();
    model.result("pg6").create("surf2", "Surface");
    model.result("pg6").feature("surf2").set("expr", "1");
    model.result("pg6").feature("surf2").create("sel1", "Selection");
    model.result("pg6").feature("surf2").feature("sel1").selection().set(30);
    model.result("pg6").run();
    model.result("pg6").feature("surf2").set("coloring", "uniform");
    model.result("pg6").feature("surf2").set("wireframe", false);
    model.result("pg6").feature("surf2").set("color", "black");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg6").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg6").run();
    model.result("pg6").feature("surf1").feature("def1").set("scaleactive", false);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg4").run();
    model.result("pg6").run();
    model.result("pg6").feature("surf1").feature("def1").active(false);
    model.result("pg6").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg6");
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result("pg6").run();
    model.result("pg6").feature("surf1").feature("def1").active(true);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("surf1").create("sel1", "Selection");
    model.result("pg6").feature("surf1").feature("sel1").selection().all();
    model.result("pg6").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 31, 32, 33, 34, 35, 36, 37, 38);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("surf1").feature().move("sel1", 0);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature().remove("surf2");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("surf1").feature("sel1").selection().set(30);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 31, 32, 33, 34, 35, 36, 37, 38);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").create("surf2", "Surface");
    model.result("pg6").feature("surf2").set("expr", "1");
    model.result("pg6").run();
    model.result("pg6").feature("surf2").create("sel1", "Selection");
    model.result("pg6").feature("surf2").feature("sel1").selection().set(30);
    model.result("pg6").run();
    model.result("pg6").set("showlegends", false);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("surf2").set("inheritrange", false);
    model.result("pg6").feature("surf2").set("inheritdeformscale", false);
    model.result("pg6").feature("surf2").set("inheritheightscale", false);
    model.result("pg6").run();
    model.result("pg6").feature("surf2").set("inheritcolor", false);
    model.result("pg6").run();
    model.result("pg6").feature("surf2").set("inheritrange", true);
    model.result("pg6").run();
    model.result("pg6").feature("surf2").set("inheritrange", false);
    model.result("pg6").feature("surf2").set("inheritplot", "surf1");
    model.result("pg6").run();
    model.result("pg6").feature("surf2").set("inheritcolor", true);
    model.result("pg6").run();
    model.result("pg6").feature("surf2").set("inheritrange", true);
    model.result("pg6").run();
    model.result("pg6").feature("surf2").set("inheritrange", false);
    model.result("pg6").feature("surf2").set("inheritcolor", false);
    model.result("pg6").feature("surf2").set("inheritrange", true);
    model.result("pg6").feature("surf2").set("inheritheightscale", true);
    model.result("pg6").feature("surf2").set("inheritrange", false);
    model.result("pg6").feature("surf2").set("inheritheightscale", false);
    model.result("pg6").feature("surf2").set("inheritcolor", true);
    model.result("pg6").feature("surf2").set("inheritdeformscale", true);
    model.result("pg6").feature("surf2").set("inheritrange", true);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("surf2").create("def1", "Deform");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("surf2").set("inheritrange", false);
    model.result("pg6").run();
    model.result("pg6").feature("surf2").set("inheritrange", true);
    model.result("pg6").feature("surf2").set("inheritheightscale", true);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("surf2").feature().remove("def1");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().remove("pg6");

    model.sol("sol3").study("std3");

    model.study("std3").feature("time").set("notlistsolnum", 1);
    model.study("std3").feature("time").set("notsolnum", "1");
    model.study("std3").feature("time").set("listsolnum", 1);
    model.study("std3").feature("time").set("solnum", "1");

    model.sol("sol3").feature().remove("t1");
    model.sol("sol3").feature().remove("v1");
    model.sol("sol3").feature().remove("st1");
    model.sol("sol3").create("st1", "StudyStep");
    model.sol("sol3").feature("st1").set("study", "std3");
    model.sol("sol3").feature("st1").set("studystep", "time");
    model.sol("sol3").create("v1", "Variables");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol3").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol3").feature("v1").set("control", "time");
    model.sol("sol3").create("t1", "Time");
    model.sol("sol3").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol3").feature("t1").set("plot", "off");
    model.sol("sol3").feature("t1").set("plotgroup", "pg1");
    model.sol("sol3").feature("t1").set("plotfreq", "tout");
    model.sol("sol3").feature("t1").set("probesel", "all");
    model.sol("sol3").feature("t1").set("probes", new String[]{});
    model.sol("sol3").feature("t1").set("probefreq", "tsteps");
    model.sol("sol3").feature("t1").set("rtol", 0.01);
    model.sol("sol3").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol3").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol3").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol3").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol3").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol3").feature("t1").set("maxorder", 2);
    model.sol("sol3").feature("t1").set("minorder", 1);
    model.sol("sol3").feature("t1").set("control", "time");
    model.sol("sol3").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol3").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol3").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol3").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol3").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol3").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol3").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol3").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol3").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol3").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol3").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol3").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol3").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol3").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol3").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol3").feature("t1").feature().remove("fcDef");
    model.sol("sol3").attach("std3");

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("Displacement (mbd)");
    model.result("pg5").set("showlooplevel", new String[]{"off", "off", "off"});
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("Surface");
    model.result("pg5").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature("surf1").feature().create("def1", "Deform");
    model.result("pg5").feature("surf1").feature("def1").label("Deformation");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("Velocity (mbd)");
    model.result("pg6").set("showlooplevel", new String[]{"off", "off", "off"});
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").feature().create("vol1", "Volume");
    model.result("pg6").feature("vol1").label("Volume");
    model.result("pg6").feature("vol1").set("expr", "mod(dom,10)");
    model.result("pg6").feature("vol1").set("unit", "1");
    model.result("pg6").feature("vol1").set("colortable", "Cyclic");
    model.result("pg6").feature("vol1").set("colorlegend", false);
    model.result("pg6").feature("vol1").set("data", "parent");
    model.result("pg6").feature("vol1").feature().create("def1", "Deform");
    model.result("pg6").feature("vol1").feature("def1").label("Deformation");
    model.result("pg6").feature().create("arwl1", "ArrowLine");
    model.result("pg6").feature("arwl1").label("Arrow Line");
    model.result("pg6").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY", "mbd.u_tZ"});
    model.result("pg6").feature("arwl1").set("placement", "elements");
    model.result("pg6").feature("arwl1").set("data", "parent");
    model.result("pg6").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg6").feature("arwl1").feature("def1").label("Deformation");

    model.sol("sol3").runAll();

    model.result("pg5").run();
    model.result("pg5").set("data", "dset3");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").run();
    model.result().remove("pg6");
    model.result("pg5").run();
    model.result("pg5").label("Cellphone");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").set("edges", false);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf1").create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().set(30);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf1").feature().remove("sel1");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").create("surf2", "Surface");
    model.result("pg5").feature("surf2").set("expr", "1");
    model.result("pg5").feature("surf2").set("coloring", "uniform");
    model.result("pg5").feature("surf2").set("color", "black");
    model.result("pg5").feature("surf2").create("sel1", "Selection");
    model.result("pg5").feature("surf2").feature("sel1").selection().set(30);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("coloring", "uniform");
    model.result("pg5").feature("surf1").set("color", "gray");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf2").create("def1", "Deform");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("inheritheightscale", false);
    model.result("pg5").feature("surf2").set("inheritdeformscale", false);
    model.result("pg5").feature("surf2").set("inheritrange", false);
    model.result("pg5").feature("surf2").set("inheritplot", "surf1");
    model.result("pg5").run();

    return model;
  }

  public static Model run14(Model model) {
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("inheritcolor", false);
    model.result("pg5").feature("surf2").set("inheritrange", true);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf1").create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 31, 32, 33, 34, 35, 36, 37, 38);

    model.component("comp1").view("view1").set("transparency", true);

    model.result("pg5").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 33, 34, 35, 36, 37, 38);
    model.result("pg5").run();

    model.component("comp1").view("view1").set("transparency", false);

    model.result("pg5").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 33, 34, 35, 36, 37, 38);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("inheritrange", false);
    model.result("pg5").feature("surf2").set("inheritdeformscale", true);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("inheritheightscale", true);
    model.result("pg5").feature("surf2").set("inheritdeformscale", false);
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("inheritheightscale", false);
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("inheritcolor", true);
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("inheritcolor", false);
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("inheritrange", true);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("inheritdeformscale", true);
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("inheritdeformscale", false);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf2").feature("def1").active(false);
    model.result("pg5").run();
    model.result("pg5").feature("surf2").feature("def1").active(true);
    model.result("pg5").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "12");
    model.result().export("anim1")
         .set("customcolor", new double[]{0.9725490212440491, 0.9725490212440491, 0.9725490212440491});
    model.result().export("anim1").set("background", "current");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "off");
    model.result().export("anim1").set("logo1d", "off");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "off");
    model.result().export("anim1").set("options2d", "on");
    model.result().export("anim1").set("title3d", "off");
    model.result().export("anim1").set("legend3d", "off");
    model.result().export("anim1").set("logo3d", "off");
    model.result().export("anim1").set("options3d", "on");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "off");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg5");
    model.result().export("anim1").run();
    model.result().export("anim1").run();
    model.result().export("anim1").set("solnumtype", "inner");
    model.result().export("anim1").run();
    model.result().export("anim1").run();
    model.result().export("anim1").set("maxframes", 200);
    model.result().export("anim1").set("plotgroup", "pg1");
    model.result().export("anim1").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("inheritrange", false);
    model.result("pg5").feature("surf2").set("inheritdeformscale", true);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("inheritcolor", false);
    model.result("pg5").feature("surf1").set("inheritdeformscale", true);
    model.result("pg5").feature("surf1").set("inheritrange", false);
    model.result("pg5").feature("surf1").set("inheritheightscale", false);
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("inheritcolor", true);
    model.result("pg5").feature("surf1").set("inheritrange", true);
    model.result("pg5").feature("surf1").set("inheritheightscale", true);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result().export("anim1").set("plotgroup", "pg4");
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result("pg4").run();
    model.result().export("anim1").showFrame();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg3").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("def1").set("scaleactive", false);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg1").active(false);
    model.result("pg1").run();
    model.result("pg5").run();
    model.result("pg5").active(false);
    model.result("pg5").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result("pg5").run();

    model.sol("sol3").study("std3");

    model.study("std3").feature("time").set("notlistsolnum", 1);
    model.study("std3").feature("time").set("notsolnum", "1");
    model.study("std3").feature("time").set("listsolnum", 1);
    model.study("std3").feature("time").set("solnum", "1");

    model.sol("sol3").feature().remove("t1");
    model.sol("sol3").feature().remove("v1");
    model.sol("sol3").feature().remove("st1");
    model.sol("sol3").create("st1", "StudyStep");
    model.sol("sol3").feature("st1").set("study", "std3");
    model.sol("sol3").feature("st1").set("studystep", "time");
    model.sol("sol3").create("v1", "Variables");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol3").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol3").feature("v1").set("control", "time");
    model.sol("sol3").create("t1", "Time");
    model.sol("sol3").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol3").feature("t1").set("plot", "off");
    model.sol("sol3").feature("t1").set("plotgroup", "pg1");
    model.sol("sol3").feature("t1").set("plotfreq", "tout");
    model.sol("sol3").feature("t1").set("probesel", "all");
    model.sol("sol3").feature("t1").set("probes", new String[]{});
    model.sol("sol3").feature("t1").set("probefreq", "tsteps");
    model.sol("sol3").feature("t1").set("rtol", 0.01);
    model.sol("sol3").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol3").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol3").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol3").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol3").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol3").feature("t1").set("maxorder", 2);
    model.sol("sol3").feature("t1").set("minorder", 1);
    model.sol("sol3").feature("t1").set("control", "time");
    model.sol("sol3").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol3").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol3").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol3").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol3").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol3").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol3").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol3").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol3").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol3").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol3").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol3").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol3").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol3").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol3").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol3").feature("t1").feature().remove("fcDef");
    model.sol("sol3").attach("std3");

    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("Displacement (mbd)");
    model.result("pg6").set("showlooplevel", new String[]{"off", "off", "off"});
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("Surface");
    model.result("pg6").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").feature("surf1").feature().create("def1", "Deform");
    model.result("pg6").feature("surf1").feature("def1").label("Deformation");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("Velocity (mbd)");
    model.result("pg7").set("showlooplevel", new String[]{"off", "off", "off"});
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").feature().create("vol1", "Volume");
    model.result("pg7").feature("vol1").label("Volume");
    model.result("pg7").feature("vol1").set("expr", "mod(dom,10)");
    model.result("pg7").feature("vol1").set("unit", "1");
    model.result("pg7").feature("vol1").set("colortable", "Cyclic");
    model.result("pg7").feature("vol1").set("colorlegend", false);
    model.result("pg7").feature("vol1").set("data", "parent");
    model.result("pg7").feature("vol1").feature().create("def1", "Deform");
    model.result("pg7").feature("vol1").feature("def1").label("Deformation");
    model.result("pg7").feature().create("arwl1", "ArrowLine");
    model.result("pg7").feature("arwl1").label("Arrow Line");
    model.result("pg7").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY", "mbd.u_tZ"});
    model.result("pg7").feature("arwl1").set("placement", "elements");
    model.result("pg7").feature("arwl1").set("data", "parent");
    model.result("pg7").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg7").feature("arwl1").feature("def1").label("Deformation");

    model.sol("sol3").runAll();

    model.result("pg6").run();
    model.result("pg6").set("data", "dset3");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").run();
    model.result().remove("pg7");
    model.result("pg6").run();
    model.result().remove("pg6");
    model.result("pg5").run();
    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").active(true);
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result("pg1").run();
    model.result("pg5").run();
    model.result("pg5").active(true);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg1").run();
    model.result("pg1").active(false);
    model.result("pg1").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").active(false);
    model.result("pg5").run();
    model.result("pg4").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").run();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result().export("anim1").set("plotgroup", "pg1");
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").active(true);
    model.result("pg1").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result("pg5").run();
    model.result("pg5").active(true);
    model.result("pg5").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "1");
    model.result("pg4").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg4");
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "mbd.disp");
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "1");
    model.result("pg4").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg4");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();

    model.label("rot2_revised_app_AG.mph");

    model.result().export("anim1").showFrame();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result().export().create("anim2", "Animation");
    model.result().export("anim2").set("target", "player");
    model.result().export("anim2").set("fontsize", "12");
    model.result().export("anim2")
         .set("customcolor", new double[]{0.9725490212440491, 0.9725490212440491, 0.9725490212440491});
    model.result().export("anim2").set("background", "current");
    model.result().export("anim2").set("gltfincludelines", "on");
    model.result().export("anim2").set("title1d", "on");
    model.result().export("anim2").set("legend1d", "off");
    model.result().export("anim2").set("logo1d", "off");
    model.result().export("anim2").set("options1d", "on");
    model.result().export("anim2").set("title2d", "on");
    model.result().export("anim2").set("legend2d", "on");
    model.result().export("anim2").set("logo2d", "off");
    model.result().export("anim2").set("options2d", "on");
    model.result().export("anim2").set("title3d", "off");
    model.result().export("anim2").set("legend3d", "off");
    model.result().export("anim2").set("logo3d", "off");
    model.result().export("anim2").set("options3d", "on");
    model.result().export("anim2").set("axisorientation", "on");
    model.result().export("anim2").set("grid", "off");
    model.result().export("anim2").set("axes1d", "on");
    model.result().export("anim2").set("axes2d", "on");
    model.result().export("anim2").set("showgrid", "on");
    model.result().export("anim2").showFrame();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg1");
    model.result().export("anim2").showFrame();
    model.result().export("anim2").set("plotgroup", "pg4");
    model.result().export("anim2").run();
    model.result().export("anim2").set("solnumtype", "inner");
    model.result().export("anim2").run();
    model.result().export().remove("anim2");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg4");
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result().export("anim1").set("framesel", "number");
    model.result("pg5").run();
    model.result().export("anim1").showFrame();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("expr", "1");
    model.result("pg5").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg5");
    model.result().export("anim1").run();
    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg5").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg5").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"-20*pi", "eta", "eta"});

    model.sol("sol3").study("std3");

    model.study("std3").feature("time").set("notlistsolnum", 1);
    model.study("std3").feature("time").set("notsolnum", "1");
    model.study("std3").feature("time").set("listsolnum", 1);
    model.study("std3").feature("time").set("solnum", "1");

    model.sol("sol3").feature().remove("t1");
    model.sol("sol3").feature().remove("v1");
    model.sol("sol3").feature().remove("st1");
    model.sol("sol3").create("st1", "StudyStep");
    model.sol("sol3").feature("st1").set("study", "std3");
    model.sol("sol3").feature("st1").set("studystep", "time");
    model.sol("sol3").create("v1", "Variables");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol3").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol3").feature("v1").set("control", "time");
    model.sol("sol3").create("t1", "Time");
    model.sol("sol3").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol3").feature("t1").set("plot", "off");
    model.sol("sol3").feature("t1").set("plotgroup", "pg1");
    model.sol("sol3").feature("t1").set("plotfreq", "tout");
    model.sol("sol3").feature("t1").set("probesel", "all");
    model.sol("sol3").feature("t1").set("probes", new String[]{});
    model.sol("sol3").feature("t1").set("probefreq", "tsteps");
    model.sol("sol3").feature("t1").set("rtol", 0.01);
    model.sol("sol3").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol3").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol3").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol3").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol3").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol3").feature("t1").set("maxorder", 2);
    model.sol("sol3").feature("t1").set("minorder", 1);
    model.sol("sol3").feature("t1").set("control", "time");
    model.sol("sol3").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol3").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol3").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol3").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol3").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol3").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol3").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol3").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol3").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol3").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol3").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol3").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol3").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol3").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol3").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol3").feature("t1").feature().remove("fcDef");
    model.sol("sol3").attach("std3");
    model.sol("sol3").runAll();

    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().export("anim1").showFrame();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf2").feature("def1").active(false);
    model.result("pg5").run();
    model.result("pg5").feature("surf2").feature("def1").active(true);
    model.result("pg5").run();
    model.result("pg5").feature("surf2").feature("def1").active(false);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf2").feature("def1").active(true);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf2").feature().remove("def1");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf2").create("def1", "Deform");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("inheritcolor", true);
    model.result("pg5").feature("surf2").set("inheritrange", true);
    model.result("pg5").feature("surf2").set("inheritdeformscale", false);
    model.result("pg5").feature("surf2").set("inheritheightscale", true);
    model.result("pg5").feature("surf2").set("inheritdeformscale", true);

    model.label("rot2_revised_app_AG.mph");

    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg4").run();

    model.study("std2").feature("time").set("geometricNonlinearity", true);

    model.sol("sol2").study("std2");

    model.study("std2").feature("time").set("notlistsolnum", 1);
    model.study("std2").feature("time").set("notsolnum", "1");
    model.study("std2").feature("time").set("listsolnum", 1);
    model.study("std2").feature("time").set("solnum", "1");

    model.sol("sol2").feature().remove("t1");
    model.sol("sol2").feature().remove("v1");
    model.sol("sol2").feature().remove("st1");
    model.sol("sol2").create("st1", "StudyStep");
    model.sol("sol2").feature("st1").set("study", "std2");
    model.sol("sol2").feature("st1").set("studystep", "time");
    model.sol("sol2").create("v1", "Variables");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol2").feature("v1").set("control", "time");
    model.sol("sol2").create("t1", "Time");

    return model;
  }

  public static Model run15(Model model) {
    model.sol("sol2").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol2").feature("t1").set("plot", "off");
    model.sol("sol2").feature("t1").set("plotgroup", "pg1");
    model.sol("sol2").feature("t1").set("plotfreq", "tout");
    model.sol("sol2").feature("t1").set("probesel", "all");
    model.sol("sol2").feature("t1").set("probes", new String[]{});
    model.sol("sol2").feature("t1").set("probefreq", "tsteps");
    model.sol("sol2").feature("t1").set("rtol", 0.01);
    model.sol("sol2").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol2").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol2").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol2").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol2").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol2").feature("t1").set("maxorder", 2);
    model.sol("sol2").feature("t1").set("minorder", 1);
    model.sol("sol2").feature("t1").set("control", "time");
    model.sol("sol2").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol2").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol2").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol2").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol2").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol2").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol2").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol2").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol2").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol2").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol2").feature("t1").feature().remove("fcDef");
    model.sol("sol2").attach("std2");
    model.sol("sol2").runAll();

    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg4").run();
    model.result().remove("pg4");
    model.result("pg5").run();
    model.result().remove("pg5");

    model.sol("sol2").study("std2");

    model.study("std2").feature("time").set("notlistsolnum", 1);
    model.study("std2").feature("time").set("notsolnum", "1");
    model.study("std2").feature("time").set("listsolnum", 1);
    model.study("std2").feature("time").set("solnum", "1");

    model.sol("sol2").feature().remove("t1");
    model.sol("sol2").feature().remove("v1");
    model.sol("sol2").feature().remove("st1");
    model.sol("sol2").create("st1", "StudyStep");
    model.sol("sol2").feature("st1").set("study", "std2");
    model.sol("sol2").feature("st1").set("studystep", "time");
    model.sol("sol2").create("v1", "Variables");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol2").feature("v1").set("control", "time");
    model.sol("sol2").create("t1", "Time");
    model.sol("sol2").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol2").feature("t1").set("plot", "off");
    model.sol("sol2").feature("t1").set("plotgroup", "pg1");
    model.sol("sol2").feature("t1").set("plotfreq", "tout");
    model.sol("sol2").feature("t1").set("probesel", "all");
    model.sol("sol2").feature("t1").set("probes", new String[]{});
    model.sol("sol2").feature("t1").set("probefreq", "tsteps");
    model.sol("sol2").feature("t1").set("rtol", 0.01);
    model.sol("sol2").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol2").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol2").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol2").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol2").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol2").feature("t1").set("maxorder", 2);
    model.sol("sol2").feature("t1").set("minorder", 1);
    model.sol("sol2").feature("t1").set("control", "time");
    model.sol("sol2").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol2").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol2").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol2").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol2").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol2").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol2").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol2").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol2").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol2").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol2").feature("t1").feature().remove("fcDef");
    model.sol("sol2").attach("std2");

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("Displacement (mbd)");
    model.result("pg4").set("showlooplevel", new String[]{"off", "off", "off"});
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("Surface");
    model.result("pg4").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature("surf1").feature().create("def1", "Deform");
    model.result("pg4").feature("surf1").feature("def1").label("Deformation");
    model.result("pg4").feature("surf1").feature("def1").set("scaleactive", true);
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("Velocity (mbd)");
    model.result("pg5").set("showlooplevel", new String[]{"off", "off", "off"});
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").feature().create("vol1", "Volume");
    model.result("pg5").feature("vol1").label("Volume");
    model.result("pg5").feature("vol1").set("expr", "mod(dom,10)");
    model.result("pg5").feature("vol1").set("unit", "1");
    model.result("pg5").feature("vol1").set("colortable", "Cyclic");
    model.result("pg5").feature("vol1").set("colorlegend", false);
    model.result("pg5").feature("vol1").set("data", "parent");
    model.result("pg5").feature("vol1").feature().create("def1", "Deform");
    model.result("pg5").feature("vol1").feature("def1").label("Deformation");
    model.result("pg5").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg5").feature().create("arwl1", "ArrowLine");
    model.result("pg5").feature("arwl1").label("Arrow Line");
    model.result("pg5").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY", "mbd.u_tZ"});
    model.result("pg5").feature("arwl1").set("placement", "elements");
    model.result("pg5").feature("arwl1").set("data", "parent");
    model.result("pg5").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg5").feature("arwl1").feature("def1").label("Deformation");
    model.result("pg5").feature("arwl1").feature("def1").set("scaleactive", true);

    model.sol("sol2").runAll();

    model.result("pg4").run();
    model.result("pg4").set("data", "dset2");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").run();
    model.result().remove("pg5");
    model.result("pg4").run();
    model.result().remove("pg4");

    model.param().set("cellphone_geom", "0");
    model.param().set("racket_geom", "1");

    model.component("comp1").geom("geom1").feature("wp10").active(false);
    model.component("comp1").geom("geom1").feature("cyl1").active(true);
    model.component("comp1").geom("geom1").feature("cyl2").active(true);
    model.component("comp1").geom("geom1").feature("pt1").active(true);

    model.param().set("racket_geom", "0");

    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.sol("sol2").study("std2");

    model.study("std2").feature("time").set("notlistsolnum", 1);
    model.study("std2").feature("time").set("notsolnum", "1");
    model.study("std2").feature("time").set("listsolnum", 1);
    model.study("std2").feature("time").set("solnum", "1");

    model.sol("sol2").feature().remove("t1");
    model.sol("sol2").feature().remove("v1");
    model.sol("sol2").feature().remove("st1");
    model.sol("sol2").create("st1", "StudyStep");
    model.sol("sol2").feature("st1").set("study", "std2");
    model.sol("sol2").feature("st1").set("studystep", "time");
    model.sol("sol2").create("v1", "Variables");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol2").feature("v1").set("control", "time");
    model.sol("sol2").create("t1", "Time");
    model.sol("sol2").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol2").feature("t1").set("plot", "off");
    model.sol("sol2").feature("t1").set("plotgroup", "pg1");
    model.sol("sol2").feature("t1").set("plotfreq", "tout");
    model.sol("sol2").feature("t1").set("probesel", "all");
    model.sol("sol2").feature("t1").set("probes", new String[]{});
    model.sol("sol2").feature("t1").set("probefreq", "tsteps");
    model.sol("sol2").feature("t1").set("rtol", 0.01);
    model.sol("sol2").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol2").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol2").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol2").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol2").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol2").feature("t1").set("maxorder", 2);
    model.sol("sol2").feature("t1").set("minorder", 1);
    model.sol("sol2").feature("t1").set("control", "time");
    model.sol("sol2").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol2").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol2").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol2").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol2").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol2").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol2").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol2").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol2").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol2").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol2").feature("t1").feature().remove("fcDef");
    model.sol("sol2").attach("std2");

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("Displacement (mbd)");
    model.result("pg4").set("showlooplevel", new String[]{"off", "off", "off"});
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("Surface");
    model.result("pg4").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature("surf1").feature().create("def1", "Deform");
    model.result("pg4").feature("surf1").feature("def1").label("Deformation");
    model.result("pg4").feature("surf1").feature("def1").set("scaleactive", true);
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("Velocity (mbd)");
    model.result("pg5").set("showlooplevel", new String[]{"off", "off", "off"});
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").feature().create("vol1", "Volume");
    model.result("pg5").feature("vol1").label("Volume");
    model.result("pg5").feature("vol1").set("expr", "mod(dom,10)");
    model.result("pg5").feature("vol1").set("unit", "1");
    model.result("pg5").feature("vol1").set("colortable", "Cyclic");
    model.result("pg5").feature("vol1").set("colorlegend", false);
    model.result("pg5").feature("vol1").set("data", "parent");
    model.result("pg5").feature("vol1").feature().create("def1", "Deform");
    model.result("pg5").feature("vol1").feature("def1").label("Deformation");
    model.result("pg5").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg5").feature().create("arwl1", "ArrowLine");
    model.result("pg5").feature("arwl1").label("Arrow Line");
    model.result("pg5").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY", "mbd.u_tZ"});
    model.result("pg5").feature("arwl1").set("placement", "elements");
    model.result("pg5").feature("arwl1").set("data", "parent");
    model.result("pg5").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg5").feature("arwl1").feature("def1").label("Deformation");
    model.result("pg5").feature("arwl1").feature("def1").set("scaleactive", true);

    model.sol("sol2").runAll();

    model.result("pg4").run();
    model.result("pg4").set("data", "dset2");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").run();
    model.result().remove("pg5");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "1");
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "gray");
    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "12");
    model.result().export("anim1")
         .set("customcolor", new double[]{0.9725490212440491, 0.9725490212440491, 0.9725490212440491});
    model.result().export("anim1").set("background", "current");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "off");
    model.result().export("anim1").set("logo1d", "off");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "off");
    model.result().export("anim1").set("options2d", "on");
    model.result().export("anim1").set("title3d", "off");
    model.result().export("anim1").set("legend3d", "off");
    model.result().export("anim1").set("logo3d", "off");
    model.result().export("anim1").set("options3d", "on");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "off");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").run();
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result().export("anim1").set("plotgroup", "pg4");
    model.result("pg4").run();
    model.result("pg4").set("edges", false);
    model.result("pg4").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result("pg4").run();
    model.result("pg4").label("T-Handle");

    model.param().set("cellphone_geom", "1");

    model.component("comp1").geom("geom1").feature("cyl1").active(false);
    model.component("comp1").geom("geom1").feature("cyl2").active(false);
    model.component("comp1").geom("geom1").feature("pt1").active(false);
    model.component("comp1").geom("geom1").feature("wp10").active(true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.study("std3").feature("time").set("geometricNonlinearity", true);

    model.sol("sol3").study("std3");

    model.study("std3").feature("time").set("notlistsolnum", 1);
    model.study("std3").feature("time").set("notsolnum", "1");
    model.study("std3").feature("time").set("listsolnum", 1);
    model.study("std3").feature("time").set("solnum", "1");

    model.sol("sol3").feature().remove("t1");
    model.sol("sol3").feature().remove("v1");
    model.sol("sol3").feature().remove("st1");
    model.sol("sol3").create("st1", "StudyStep");
    model.sol("sol3").feature("st1").set("study", "std3");
    model.sol("sol3").feature("st1").set("studystep", "time");
    model.sol("sol3").create("v1", "Variables");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol3").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol3").feature("v1").set("control", "time");
    model.sol("sol3").create("t1", "Time");
    model.sol("sol3").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol3").feature("t1").set("plot", "off");
    model.sol("sol3").feature("t1").set("plotgroup", "pg1");
    model.sol("sol3").feature("t1").set("plotfreq", "tout");
    model.sol("sol3").feature("t1").set("probesel", "all");
    model.sol("sol3").feature("t1").set("probes", new String[]{});
    model.sol("sol3").feature("t1").set("probefreq", "tsteps");
    model.sol("sol3").feature("t1").set("rtol", 0.01);
    model.sol("sol3").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol3").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol3").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol3").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol3").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol3").feature("t1").set("maxorder", 2);
    model.sol("sol3").feature("t1").set("minorder", 1);
    model.sol("sol3").feature("t1").set("control", "time");
    model.sol("sol3").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol3").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol3").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol3").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol3").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol3").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol3").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol3").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol3").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol3").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol3").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol3").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol3").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol3").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol3").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol3").feature("t1").feature().remove("fcDef");
    model.sol("sol3").attach("std3");

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("Displacement (mbd)");
    model.result("pg5").set("showlooplevel", new String[]{"off", "off", "off"});
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("Surface");
    model.result("pg5").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature("surf1").feature().create("def1", "Deform");
    model.result("pg5").feature("surf1").feature("def1").label("Deformation");
    model.result("pg5").feature("surf1").feature("def1").set("scaleactive", true);
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("Velocity (mbd)");
    model.result("pg6").set("showlooplevel", new String[]{"off", "off", "off"});
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").feature().create("vol1", "Volume");
    model.result("pg6").feature("vol1").label("Volume");
    model.result("pg6").feature("vol1").set("expr", "mod(dom,10)");
    model.result("pg6").feature("vol1").set("unit", "1");
    model.result("pg6").feature("vol1").set("colortable", "Cyclic");
    model.result("pg6").feature("vol1").set("colorlegend", false);
    model.result("pg6").feature("vol1").set("data", "parent");
    model.result("pg6").feature("vol1").feature().create("def1", "Deform");
    model.result("pg6").feature("vol1").feature("def1").label("Deformation");
    model.result("pg6").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg6").feature().create("arwl1", "ArrowLine");
    model.result("pg6").feature("arwl1").label("Arrow Line");
    model.result("pg6").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY", "mbd.u_tZ"});
    model.result("pg6").feature("arwl1").set("placement", "elements");
    model.result("pg6").feature("arwl1").set("data", "parent");
    model.result("pg6").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg6").feature("arwl1").feature("def1").label("Deformation");
    model.result("pg6").feature("arwl1").feature("def1").set("scaleactive", true);

    model.sol("sol3").runAll();

    model.result("pg5").run();
    model.result("pg5").set("data", "dset3");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").run();
    model.result().remove("pg6");
    model.result("pg5").run();
    model.result("pg5").label("Cellphone");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 2, 0);
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("expr", "1");
    model.result("pg5").feature("surf1").set("coloring", "uniform");
    model.result("pg5").feature("surf1").set("color", "gray");
    model.result("pg5").run();
    model.result("pg5").set("edges", false);
    model.result("pg5").run();
    model.result("pg5").create("surf2", "Surface");
    model.result("pg5").feature("surf2").set("expr", "1");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf1").create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().set(11);

    model.component("comp1").view("view1").set("transparency", true);

    model.result("pg5").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 31, 32, 33, 34, 35, 36, 37, 38);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf2").create("sel1", "Selection");
    model.result("pg5").feature("surf2").feature("sel1").selection().set(30);
    model.result("pg5").run();

    model.component("comp1").view("view1").set("transparency", false);

    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("coloring", "uniform");
    model.result("pg5").feature("surf2").set("color", "black");
    model.result("pg5").feature("surf2").set("inheritcolor", false);
    model.result("pg5").feature("surf2").set("inheritrange", false);
    model.result("pg5").feature("surf2").set("inheritheightscale", false);
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("inheritcolor", true);
    model.result("pg5").feature("surf2").set("inheritrange", true);
    model.result("pg5").feature("surf2").set("inheritplot", "surf1");
    model.result("pg5").feature("surf2").set("inheritcolor", false);
    model.result("pg5").feature("surf2").set("inheritrange", false);
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("smooth", "everywhere");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("smooth", "internal");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("smooth", "everywhere");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("smooth", "material");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().export("anim1").showFrame();
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("resolution", "extrafine");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("resolution", "coarse");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("resolution", "normal");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("smooth", "everywhere");
    model.result("pg5").feature("surf2").set("recover", "ppr");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("recover", false);
    model.result("pg5").feature("surf2").set("smooth", "material");
    model.result("pg5").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg5");
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf2").create("def1", "Deform");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("stopped", true);
    model.result().export("anim1").run();

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"-10*pi", "eta", "eta"});

    model.sol("sol3").study("std3");

    model.study("std3").feature("time").set("notlistsolnum", 1);
    model.study("std3").feature("time").set("notsolnum", "1");
    model.study("std3").feature("time").set("listsolnum", 1);
    model.study("std3").feature("time").set("solnum", "1");

    model.sol("sol3").feature().remove("t1");
    model.sol("sol3").feature().remove("v1");
    model.sol("sol3").feature().remove("st1");
    model.sol("sol3").create("st1", "StudyStep");
    model.sol("sol3").feature("st1").set("study", "std3");
    model.sol("sol3").feature("st1").set("studystep", "time");
    model.sol("sol3").create("v1", "Variables");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol3").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");

    return model;
  }

  public static Model run16(Model model) {
    model.sol("sol3").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.15486434063300206");
    model.sol("sol3").feature("v1").set("control", "time");
    model.sol("sol3").create("t1", "Time");
    model.sol("sol3").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol3").feature("t1").set("plot", "off");
    model.sol("sol3").feature("t1").set("plotgroup", "pg1");
    model.sol("sol3").feature("t1").set("plotfreq", "tout");
    model.sol("sol3").feature("t1").set("probesel", "all");
    model.sol("sol3").feature("t1").set("probes", new String[]{});
    model.sol("sol3").feature("t1").set("probefreq", "tsteps");
    model.sol("sol3").feature("t1").set("rtol", 0.01);
    model.sol("sol3").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol3").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol3").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol3").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol3").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol3").feature("t1").set("maxorder", 2);
    model.sol("sol3").feature("t1").set("minorder", 1);
    model.sol("sol3").feature("t1").set("control", "time");
    model.sol("sol3").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol3").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol3").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol3").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol3").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol3").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol3").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol3").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol3").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol3").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol3").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol3").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol3").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol3").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol3").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol3").feature("t1").feature().remove("fcDef");
    model.sol("sol3").attach("std3");
    model.sol("sol3").runAll();

    model.result("pg5").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").run();

    model.label("rot2_revised_app_AG.mph");

    model.result().export("anim1").showFrame();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").run();

    model.param().set("cellphone_geom", "0");
    model.param().set("racket_geom", "1");

    model.component("comp1").geom("geom1").feature("wp10").active(false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").mesh("mesh2").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.773475924425763");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.773475924425763");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 141, 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"4*pi", "eta", "eta"});

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.773475924425763");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.773475924425763");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.component("comp1").mesh("mesh2").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.773475924425763");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.773475924425763");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.study("std1").feature("time").set("tunit", "s");
    model.study("std1").feature("time").set("geometricNonlinearity", true);

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.773475924425763");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.773475924425763");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.param().set("racket_geom", "0");

    model.component("comp1").geom("geom1").feature("cyl1").active(true);
    model.component("comp1").geom("geom1").feature("cyl2").active(true);
    model.component("comp1").geom("geom1").feature("pt1").active(true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("mbd").feature("rd1").feature("init1").set("Omega", new int[]{0, 0, 0});
    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"eta", "eta", "-20*pi"});

    model.component("comp1").mesh("mesh1").run();

    model.sol("sol2").study("std2");

    model.study("std2").feature("time").set("notlistsolnum", 1);
    model.study("std2").feature("time").set("notsolnum", "1");
    model.study("std2").feature("time").set("listsolnum", 1);
    model.study("std2").feature("time").set("solnum", "1");

    model.sol("sol2").feature().remove("t1");
    model.sol("sol2").feature().remove("v1");
    model.sol("sol2").feature().remove("st1");
    model.sol("sol2").create("st1", "StudyStep");
    model.sol("sol2").feature("st1").set("study", "std2");
    model.sol("sol2").feature("st1").set("studystep", "time");
    model.sol("sol2").create("v1", "Variables");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol2").feature("v1").set("control", "time");
    model.sol("sol2").create("t1", "Time");
    model.sol("sol2").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol2").feature("t1").set("plot", "off");
    model.sol("sol2").feature("t1").set("plotgroup", "pg1");
    model.sol("sol2").feature("t1").set("plotfreq", "tout");
    model.sol("sol2").feature("t1").set("probesel", "all");
    model.sol("sol2").feature("t1").set("probes", new String[]{});
    model.sol("sol2").feature("t1").set("probefreq", "tsteps");
    model.sol("sol2").feature("t1").set("rtol", 0.01);
    model.sol("sol2").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol2").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol2").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol2").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol2").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol2").feature("t1").set("maxorder", 2);
    model.sol("sol2").feature("t1").set("minorder", 1);
    model.sol("sol2").feature("t1").set("control", "time");
    model.sol("sol2").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol2").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol2").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol2").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol2").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol2").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol2").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol2").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol2").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol2").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol2").feature("t1").feature().remove("fcDef");
    model.sol("sol2").attach("std2");
    model.sol("sol2").runAll();

    model.result("pg4").run();

    model.param().set("racket_geom", "1");

    model.component("comp1").geom("geom1").feature("cyl1").active(false);
    model.component("comp1").geom("geom1").feature("cyl2").active(false);
    model.component("comp1").geom("geom1").feature("pt1").active(false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"4*pi", "eta", "eta"});
    model.component("comp1").physics("mbd").feature("rd1").feature("init1").set("Omega", new int[]{1, 0, 0});

    model.component("comp1").mesh("mesh2").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.7734759244257629");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.7734759244257629");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

//    Started to run method mesh_racket

    model.component("comp1").mesh("mesh2").feature("ftri1").selection().set(115, 117);
    model.component("comp1").mesh("mesh2").feature("swe1").selection().set(1, 2, 3, 4, 11, 12, 13, 14);
    model.component("comp1").mesh("mesh2").feature("swe1").selection("sourceface").set(115, 117);
    model.component("comp1").mesh("mesh2").feature("ftet1").selection().set(5, 6, 15, 16);
    model.component("comp1").mesh("mesh2").feature("ftet2").selection().set(7, 8, 10);
    model.component("comp1").mesh("mesh2").feature("swe2").selection().set(9);
    model.component("comp1").mesh("mesh2").feature("swe2").selection("sourceface").set(95);
    model.component("comp1").mesh("mesh2").run();

//    Finished running method mesh_racket

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.7734759244257629");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.7734759244257629");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");

    return model;
  }

  public static Model run17(Model model) {
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();

//    Started to run method mesh_racket

    model.component("comp1").mesh("mesh2").feature("ftri1").selection().set(115, 117);
    model.component("comp1").mesh("mesh2").feature("swe1").selection().set(1, 2, 3, 4, 11, 12, 13, 14);
    model.component("comp1").mesh("mesh2").feature("swe1").selection("sourceface").set(115, 117);
    model.component("comp1").mesh("mesh2").feature("ftet1").selection().set(5, 6, 15, 16);
    model.component("comp1").mesh("mesh2").feature("ftet2").selection().set(7, 8, 10);
    model.component("comp1").mesh("mesh2").feature("swe2").selection().set(9);
    model.component("comp1").mesh("mesh2").feature("swe2").selection("sourceface").set(95);
    model.component("comp1").mesh("mesh2").run();

//    Finished running method mesh_racket

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.7734759244257629");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.7734759244257629");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("sel1").selection()
         .set(4, 5, 8, 9, 12, 13, 19, 20, 23, 24, 27, 28, 39, 40, 43, 44, 47, 48, 51, 52, 54, 57, 59, 62, 64, 71, 73, 76, 78, 80, 82, 84, 91, 98, 100, 102, 104, 106, 108, 116, 118, 121, 123, 125, 126, 127, 128, 129, 130, 131, 132, 153, 155, 157, 161, 163, 165, 169, 171, 173, 177, 179, 181);
    model.result("pg1").run();
    model.result("pg1").feature("surf3").feature("sel1").selection()
         .set(6, 7, 10, 11, 14, 15, 21, 22, 25, 26, 29, 30, 41, 42, 45, 46, 49, 50, 55, 56, 58, 60, 63, 65, 72, 74, 77, 79, 81, 83, 85, 97, 99, 101, 103, 105, 107, 109, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 154, 156, 158, 162, 164, 166, 170, 172, 174, 175, 178, 180, 182);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 6, 7, 16, 17, 18, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 41, 42, 53, 60, 61, 65, 66, 67, 68, 69, 70, 75, 86, 87, 88, 89, 90, 92, 93, 94, 95, 96, 110, 111, 112, 113, 114, 115, 117, 119, 120, 122, 124, 133, 134, 135, 136, 137, 138, 139, 140, 149, 150, 151, 152, 154, 159, 160, 166, 167, 168, 170, 176, 178);
    model.result("pg1").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg1");

    model.label("rot2_revised_app_AG.mph");

    model.result().export("anim1").showFrame();

    model.param().set("racket_geom", "0");

    model.component("comp1").geom("geom1").feature("cyl1").active(true);
    model.component("comp1").geom("geom1").feature("cyl2").active(true);
    model.component("comp1").geom("geom1").feature("pt1").active(true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").runPre("ext1");
    model.component("comp1").geom("geom1").feature("wp10").active(true);

    model.param().set("cellphone_geom", "1");

    model.component("comp1").geom("geom1").feature("cyl1").active(false);
    model.component("comp1").geom("geom1").feature("cyl2").active(false);
    model.component("comp1").geom("geom1").feature("pt1").active(false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.param().set("cellphone_geom", "0");

    model.component("comp1").geom("geom1").feature("cyl1").active(true);
    model.component("comp1").geom("geom1").feature("cyl2").active(true);
    model.component("comp1").geom("geom1").feature("pt1").active(true);
    model.component("comp1").geom("geom1").feature("wp10").active(false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("mbd").feature("rd1").feature("init1").set("Omega", new int[]{0, 0, 1});
    model.component("comp1").physics("mbd").feature("rd1").feature("init1")
         .set("omega", new String[]{"eta", "eta", "-20*pi"});

    model.component("comp1").mesh("mesh1").run();

    model.sol("sol2").study("std2");

    model.study("std2").feature("time").set("notlistsolnum", 1);
    model.study("std2").feature("time").set("notsolnum", "1");
    model.study("std2").feature("time").set("listsolnum", 1);
    model.study("std2").feature("time").set("solnum", "1");

    model.sol("sol2").feature().remove("t1");
    model.sol("sol2").feature().remove("v1");
    model.sol("sol2").feature().remove("st1");
    model.sol("sol2").create("st1", "StudyStep");
    model.sol("sol2").feature("st1").set("study", "std2");
    model.sol("sol2").feature("st1").set("studystep", "time");
    model.sol("sol2").create("v1", "Variables");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol2").feature("v1").set("control", "time");
    model.sol("sol2").create("t1", "Time");
    model.sol("sol2").feature("t1").set("tlist", "range(0,0.015,2.1)");
    model.sol("sol2").feature("t1").set("plot", "off");
    model.sol("sol2").feature("t1").set("plotgroup", "pg1");
    model.sol("sol2").feature("t1").set("plotfreq", "tout");
    model.sol("sol2").feature("t1").set("probesel", "all");
    model.sol("sol2").feature("t1").set("probes", new String[]{});
    model.sol("sol2").feature("t1").set("probefreq", "tsteps");
    model.sol("sol2").feature("t1").set("rtol", 0.01);
    model.sol("sol2").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol2").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol2").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol2").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol2").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol2").feature("t1").set("maxorder", 2);
    model.sol("sol2").feature("t1").set("minorder", 1);
    model.sol("sol2").feature("t1").set("control", "time");
    model.sol("sol2").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol2").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol2").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol2").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol2").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol2").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol2").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol2").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol2").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol2").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol2").feature("t1").feature().remove("fcDef");
    model.sol("sol2").attach("std2");
    model.sol("sol2").runAll();

    model.result("pg4").run();
    model.result("pg5").run();

    model.param().set("cellphone_geom", "1");

    model.component("comp1").geom("geom1").feature("cyl1").active(false);
    model.component("comp1").geom("geom1").feature("cyl2").active(false);
    model.component("comp1").geom("geom1").feature("pt1").active(false);
    model.component("comp1").geom("geom1").run("wp10");
    model.component("comp1").geom("geom1").feature("wp10").active(true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.param().set("cellphone_geom", "0");

    model.component("comp1").geom("geom1").feature("cyl1").active(true);
    model.component("comp1").geom("geom1").feature("cyl2").active(true);
    model.component("comp1").geom("geom1").feature("pt1").active(true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.label("rot2_revised_app_AG.mph");

    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg1").run();

    model.label("rot2_revised_app_AG.mph");

    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg4").run();

    model.label("rot2_revised_app_AG.mph");

    model.result("pg4").run();
    model.result("pg1").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg5");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("titletype", "auto");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("titletype", "none");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("titletype", "none");
    model.result("pg1").run();
    model.result("pg1").feature("surf3").set("titletype", "none");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("titletype", "none");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("titletype", "none");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("titletype", "none");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf4", "surf1");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 141, 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf4").set("data", "dset1");
    model.result("pg1").run();
    model.result("pg1").feature("surf4").setIndex("looplevel", 1, 0);
    model.result("pg1").run();

    model.component("comp1").view().duplicate("view14", "view1");
    model.component("comp1").view("view14").set("locked", true);

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature().remove("surf4");
    model.result("pg1").run();
    model.result("pg1").set("view", "view14");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf4", "surf1");
    model.result("pg1").run();
    model.result("pg1").feature("surf4").set("data", "dset1");
    model.result("pg1").run();
    model.result("pg1").feature("surf4").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf4").feature("sel1").selection().set(9);
    model.result("pg1").run();

    model.component("comp1").view("view14").set("locked", true);

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature().remove("surf4");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf4", "surf1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf4").set("data", "dset1");
    model.result("pg1").feature("surf4").setIndex("looplevel", 1, 0);
    model.result("pg1").run();

    model.component("comp1").view("view14").set("locked", true);

    model.result("pg1").run();
    model.result("pg1").feature().remove("surf4");
    model.result("pg1").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg1");
    model.result().export("anim1").run();
    model.result().export("anim1").set("showframe", 141);
    model.result().export("anim1").showFrame();
    model.result("pg1").run();
    model.result().export("anim1").showFrame();
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").run();

    model.label("rot2_revised_app_AG.mph");

    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf2", "surf1");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").set("data", "dset2");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").run();

    model.component("comp1").view().create("view15", "geom1");

    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg4").run();

    model.component("comp1").view("view15").set("locked", true);

    model.result("pg4").run();
    model.result("pg4").feature().remove("surf2");
    model.result("pg4").run();
    model.result("pg4").set("view", "view15");

    model.component("comp1").view("view15").set("showgrid", false);

    model.result("pg5").run();
    model.result("pg5").feature().duplicate("surf3", "surf1");
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 141, 0);
    model.result("pg5").run();
    model.result("pg5").feature("surf3").set("data", "dset3");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();

    model.component("comp1").view().duplicate("view16", "view15");
    model.component("comp1").view("view16").set("rotcenlocked", false);
    model.component("comp1").view("view16").set("locked", true);

    model.result("pg5").run();
    model.result("pg5").set("view", "view16");
    model.result("pg5").run();
    model.result("pg5").feature().remove("surf3");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();

    model.component("comp1").view().remove("view16");

    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("surf3", "surf1");
    model.result("pg5").run();
    model.result("pg5").feature("surf3").set("data", "dset3");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();

    model.component("comp1").view().duplicate("view16", "view15");
    model.component("comp1").view("view16").set("locked", true);

    model.result("pg5").run();
    model.result("pg5").feature().remove("surf3");
    model.result("pg5").run();
    model.result("pg5").set("view", "view16");
    model.result("pg4").run();

    model.component("comp1").view().remove("view15");

    model.result("pg5").run();
    model.result("pg4").run();

    model.component("comp1").view("view14").label("Tennis Racket View");
    model.component("comp1").view("view16").label("Cellphone View");

    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf2", "surf1");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").set("data", "dset2");
    model.result("pg5").run();
    model.result("pg4").run();

    model.component("comp1").view().duplicate("view17", "view16");
    model.component("comp1").view("view17").set("locked", false);
    model.component("comp1").view().remove("view17");
    model.component("comp1").view().duplicate("view17", "view16");
    model.component("comp1").view("view17").set("locked", true);

    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").set("view", "view17");
    model.result("pg4").run();
    model.result("pg4").feature().remove("surf2");
    model.result("pg4").run();

    model.component("comp1").view("view17").label("T-Handle View");

    model.result("pg1").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg4").run();
    model.result("pg5").run();

    model.label("rot2_revised_app_AG.mph");

    model.result("pg5").run();
    model.result("pg1").run();
    model.result("pg5").run();
    model.result("pg1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.015,2.8)");
    model.study("std2").feature("time").set("tlist", "range(0,0.015,3)");
    model.study("std1").feature("time").set("tlist", "range(0,0.015,3)");
    model.study("std3").feature("time").set("tlist", "range(0,0.015,3)");
    model.study("std2").feature("time").set("tlist", "range(0,0.015,3)");
    model.study("std1").feature("time").set("tlist", "range(0,0.015,2.5)");
    model.study("std2").feature("time").set("tlist", "range(0,0.025,4)");
    model.study("std1").feature("time").set("tlist", "range(0,0.015,2.5)");
    model.study("std3").feature("time").set("tlist", "range(0,0.015,3)");

    model.label("rot2_revised_app_AG.mph");
    model.label("rot2_revised_app_AG.mph");
    model.label("rot2_revised_app_AG.mph");

    model.study("std3").feature("time").set("tlist", "range(0,0.015,2.5)");

    model.label("rot2_revised_app_AG.mph");
    model.label("rot2_revised_app_AG.mph");
    model.label("rot2_revised_app_AG.mph");

    model.result().export("anim1").showFrame();
    model.result().export("anim1").showFrame();

    model.label("dzhanibekov_effect_app.mph");

    model.result().export("anim1").showFrame();

    model.label("dzhanibekov_effect_app_editDE.mph");
    model.label("dzhanibekov-effect-1.mph");

    model.component("comp1").geom("geom1").run("fin");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "1");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "1");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.5)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.01);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg5").run();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol2").study("std2");

    model.study("std2").feature("time").set("notlistsolnum", 1);
    model.study("std2").feature("time").set("notsolnum", "1");
    model.study("std2").feature("time").set("listsolnum", 1);
    model.study("std2").feature("time").set("solnum", "1");

    model.sol("sol2").feature().remove("t1");
    model.sol("sol2").feature().remove("v1");
    model.sol("sol2").feature().remove("st1");
    model.sol("sol2").create("st1", "StudyStep");
    model.sol("sol2").feature("st1").set("study", "std2");
    model.sol("sol2").feature("st1").set("studystep", "time");
    model.sol("sol2").create("v1", "Variables");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol2").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol2").feature("v1").set("control", "time");
    model.sol("sol2").create("t1", "Time");
    model.sol("sol2").feature("t1").set("tlist", "range(0,0.025,4)");
    model.sol("sol2").feature("t1").set("plot", "off");
    model.sol("sol2").feature("t1").set("plotgroup", "pg1");
    model.sol("sol2").feature("t1").set("plotfreq", "tout");
    model.sol("sol2").feature("t1").set("probesel", "all");
    model.sol("sol2").feature("t1").set("probes", new String[]{});

    return model;
  }

  public static Model run18(Model model) {
    model.sol("sol2").feature("t1").set("probefreq", "tsteps");
    model.sol("sol2").feature("t1").set("rtol", 0.01);
    model.sol("sol2").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol2").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol2").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol2").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol2").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol2").feature("t1").set("maxorder", 2);
    model.sol("sol2").feature("t1").set("minorder", 1);
    model.sol("sol2").feature("t1").set("control", "time");
    model.sol("sol2").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol2").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol2").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol2").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol2").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol2").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol2").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol2").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol2").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol2").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol2").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol2").feature("t1").feature().remove("fcDef");
    model.sol("sol2").attach("std2");
    model.sol("sol2").runAll();

    model.result("pg4").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg4");

    model.label("55_dzhanibekov-effect.mph");

    model.component("comp1").geom("geom1").designBooleans(true);
    model.component("comp1").geom("geom1").run("fin");

    model.param().set("racket_geom", "1");

    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").runPre("del1");

    model.component("comp1").view("view14").set("renderwireframe", true);

    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").runPre("del1");
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").runPre("del1");
    model.component("comp1").geom("geom1").feature().remove("del2");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").mesh("mesh2").feature().remove("ftri1");
    model.component("comp1").mesh("mesh2").feature().remove("swe1");
    model.component("comp1").mesh("mesh2").feature().remove("ftet1");
    model.component("comp1").mesh("mesh2").feature().remove("ftet2");
    model.component("comp1").mesh("mesh2").feature().remove("swe2");
    model.component("comp1").mesh("mesh2").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "auto");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.773072730943329");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.773072730943329");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.5)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.001);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("eventtol", 0.01);
    model.sol("sol1").feature("t1").set("reacf", true);
    model.sol("sol1").feature("t1").set("storeudot", true);
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("endtimeinterpolation", true);
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("rescaleafterinitbw", false);
    model.sol("sol1").feature("t1").set("bwinitstepfrac", "0.001");
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.component("comp1").mesh("mesh2").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh2").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "auto");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.773072730943329");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.773072730943329");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.5)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.001);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("eventtol", 0.01);
    model.sol("sol1").feature("t1").set("reacf", true);
    model.sol("sol1").feature("t1").set("storeudot", true);
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("endtimeinterpolation", true);
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("rescaleafterinitbw", false);
    model.sol("sol1").feature("t1").set("bwinitstepfrac", "0.001");
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();

    model.component("comp1").mesh("mesh1").run();

    model.result("pg1").run();
    model.result("pg1").run();

    model.component("comp1").geom("geom1").feature("cyl1").active(false);
    model.component("comp1").geom("geom1").feature("cyl2").active(false);
    model.component("comp1").geom("geom1").feature("pt1").active(false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").mesh("mesh2").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "auto");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.773072730943329");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.773072730943329");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.5)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.001);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("eventtol", 0.01);
    model.sol("sol1").feature("t1").set("reacf", true);
    model.sol("sol1").feature("t1").set("storeudot", true);
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("endtimeinterpolation", true);
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("rescaleafterinitbw", false);
    model.sol("sol1").feature("t1").set("bwinitstepfrac", "0.001");
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.component("comp1").mesh("mesh2").run();

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "auto");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.773072730943329");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.773072730943329");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.5)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.001);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("eventtol", 0.01);
    model.sol("sol1").feature("t1").set("reacf", true);
    model.sol("sol1").feature("t1").set("storeudot", true);
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("endtimeinterpolation", true);
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("rescaleafterinitbw", false);
    model.sol("sol1").feature("t1").set("bwinitstepfrac", "0.001");
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();

    model.component("comp1").view("view14").camera().set("projection", "orthographic");

    model.result("pg1").feature("surf1").feature("sel1").selection().set();

    model.component("comp1").view("view14").set("renderwireframe", false);

    model.result("pg1").feature("surf1").feature("sel1").selection()
         .set(2, 3, 4, 5, 6, 7, 38, 40, 42, 68, 69, 85, 87, 88, 89, 92, 93, 109, 128, 131, 132, 135, 136, 139, 164, 165, 166);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("sel1").selection().set(14, 15, 21, 22, 144, 145);

    model.component("comp1").view("view14").set("renderwireframe", false);

    model.result("pg1").feature("surf2").feature("sel1").selection()
         .set(14, 15, 21, 22, 50, 55, 68, 72, 73, 75, 76, 77, 78, 79, 80, 81, 82, 84, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 144, 145, 146, 147, 157, 161);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("sel1").selection()
         .set(14, 15, 21, 22, 50, 55, 73, 76, 78, 80, 82, 84, 98, 100, 102, 104, 106, 144, 145, 146, 147, 157, 161);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf3").feature("sel1").selection()
         .set(12, 13, 19, 20, 48, 53, 97, 99, 101, 103, 105, 115, 117, 120, 122, 156, 160);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();

    model.component("comp1").geom("geom1").feature("cyl1").active(true);
    model.component("comp1").geom("geom1").feature("cyl2").active(true);
    model.component("comp1").geom("geom1").feature("pt1").active(true);

    model.param().set("racket_geom", "0");

    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh2").feature().remove("ftri2");
    model.component("comp1").mesh("mesh2").run();

    model.param().set("racket_geom", "1");

    model.component("comp1").geom("geom1").feature("cyl1").active(false);
    model.component("comp1").geom("geom1").feature("cyl2").active(false);
    model.component("comp1").geom("geom1").feature("pt1").active(false);

    model.label("61_dzhanibekov-effect_fix_try.mph");

    model.component("comp1").geom("geom1").run("fin");

    model.param().set("racket_geom", "0");

    model.component("comp1").geom("geom1").feature("cyl1").active(true);
    model.component("comp1").geom("geom1").feature("cyl2").active(true);
    model.component("comp1").geom("geom1").feature("pt1").active(true);
    model.component("comp1").geom("geom1").run("fin");

    model.label("61_dzhanibekov-effect_fix_try.mph");

    model.param().set("racket_geom", "1");

    model.component("comp1").geom("geom1").feature("cyl1").active(false);
    model.component("comp1").geom("geom1").feature("cyl2").active(false);
    model.component("comp1").geom("geom1").feature("pt1").active(false);
    model.component("comp1").geom("geom1").run("fin");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "auto");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.773072730943329");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.773072730943329");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.5)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.001);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("eventtol", 0.01);
    model.sol("sol1").feature("t1").set("reacf", true);
    model.sol("sol1").feature("t1").set("storeudot", true);
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("endtimeinterpolation", true);
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("rescaleafterinitbw", false);
    model.sol("sol1").feature("t1").set("bwinitstepfrac", "0.001");
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("sel1").selection()
         .set(2, 3, 4, 5, 6, 7, 38, 40, 42, 63, 64, 65, 68, 69, 85, 87, 88, 89, 92, 93, 109, 128, 131, 132, 135, 136, 139, 151, 152, 153, 164, 165, 166);
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("sel1").selection()
         .set(10, 11, 14, 15, 21, 22, 46, 50, 55, 62, 73, 76, 78, 80, 82, 84, 98, 100, 102, 104, 106, 140, 143, 144, 145, 146, 147, 155, 157, 161, 163);
    model.result("pg1").run();
    model.result("pg1").feature("surf3").feature("sel1").selection()
         .set(8, 9, 12, 13, 19, 20, 44, 48, 53, 61, 97, 99, 101, 103, 105, 115, 117, 120, 122, 124, 127, 154, 156, 160, 162);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf3").feature("sel1").selection()
         .set(8, 9, 12, 13, 19, 20, 44, 48, 53, 61, 72, 75, 77, 79, 81, 97, 99, 101, 103, 105, 115, 117, 120, 122, 124, 127, 154, 156, 160, 162);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf3").feature("sel1").selection()
         .set(8, 9, 12, 13, 19, 20, 44, 48, 53, 61, 70, 72, 75, 77, 79, 81, 83, 90, 97, 99, 101, 103, 105, 107, 115, 117, 120, 122, 124, 127, 154, 156, 160, 162);
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("sel1").selection()
         .set(10, 11, 14, 15, 21, 22, 46, 50, 55, 62, 71, 73, 76, 78, 80, 82, 84, 96, 98, 100, 102, 104, 106, 108, 140, 143, 144, 145, 146, 147, 155, 157, 161, 163);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();

    model.component("comp1").geom("geom1").feature("cyl1").active(true);
    model.component("comp1").geom("geom1").feature("cyl2").active(true);
    model.component("comp1").geom("geom1").feature("pt1").active(true);

    model.param().set("racket_geom", "0");

    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "auto");

    model.sol("sol1").feature().remove("t1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");

    return model;
  }

  public static Model run19(Model model) {
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_b").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rd1_a").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.12961481396815722");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.015,2.5)");
    model.sol("sol1").feature("t1").set("plot", "on");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.001);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("eventtol", 0.01);
    model.sol("sol1").feature("t1").set("reacf", true);
    model.sol("sol1").feature("t1").set("storeudot", true);
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("endtimeinterpolation", true);
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("rescaleafterinitbw", false);
    model.sol("sol1").feature("t1").set("bwinitstepfrac", "0.001");
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.component("comp1").geom("geom1").run("fin");

    model.label("61_dzhanibekov-effect.mph");

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();

    model.component("comp1").view("view14").set("locked", false);
    model.component("comp1").view("view16").set("locked", false);
    model.component("comp1").view("view17").set("locked", false);

    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("showframe", 300);
    model.result().export("anim1").showFrame();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("framesel", "number");
    model.result().export("anim1").set("maxframes", 300);

    model.label("61_dzhanibekov-effect.mph");

    model.result().export("anim1").showFrame();

    model.study("std2").feature("time").set("tlist", "range(0,0.015,4)");

    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("framesel", "all");

    model.label("61_dzhanibekov-effect.mph");

    model.result().export("anim1").showFrame();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();

    model.param().set("racket_geom", "1");

    model.component("comp1").geom("geom1").feature("cyl1").active(false);
    model.component("comp1").geom("geom1").feature("cyl2").active(false);
    model.component("comp1").geom("geom1").feature("pt1").active(false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view14").set("locked", true);

    model.param().set("racket_geom", "0");
    model.param().set("cellphone_geom", "1");

    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view16").set("locked", true);

    model.component("comp1").geom("geom1").feature("cyl1").active(true);
    model.component("comp1").geom("geom1").feature("cyl2").active(true);
    model.component("comp1").geom("geom1").feature("pt1").active(true);

    model.param().set("cellphone_geom", "0");

    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("locked", true);

    model.label("dzhanibekov-effect.mph");
    model.label("dzhanibekov-effect.mph");

    model.result("pg4").run();
    model.result("pg1").run();

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    model = run4(model);
    model = run5(model);
    model = run6(model);
    model = run7(model);
    model = run8(model);
    model = run9(model);
    model = run10(model);
    model = run11(model);
    model = run12(model);
    model = run13(model);
    model = run14(model);
    model = run15(model);
    model = run16(model);
    model = run17(model);
    model = run18(model);
    run19(model);
  }

}
