package online.luhmirin.visionapiexperiment.preview.detector;


import java.util.List;

public interface DetectorListener<T> {

    void onResultFound(List<T> results);

}
