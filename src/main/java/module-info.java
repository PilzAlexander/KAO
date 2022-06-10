module kao.rgbhistogramm {
    requires aparapi;
    requires java.base;
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    exports kao.rgbhistogramm;
    exports kao.kantenerkennung;
    exports kao.arraypacking;
    exports kao.radixsort;
    exports kao.matrixvektormultiplikation;
    exports kao.reduziereprufung;
    exports kao.gauss;
    exports kao;
}