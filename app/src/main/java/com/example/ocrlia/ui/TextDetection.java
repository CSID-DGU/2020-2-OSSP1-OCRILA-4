package com.example.ocrlia.ui;

public class TextDetection {
    annotateImageRequest.setFeatures(new ArrayList<Feature>() {{
        Feature textDetection = new Feature();
        textDetection.setType("TEXT_DETECTION");
        textDetection.setMaxResults(10);
        add(textDetection);
    }});


    private static String convertResponseToString(BatchAnnotateImagesResponse response) {
        StringBuilder message = new StringBuilder("I found these things:\n\n");

        List<EntityAnnotation> labels = response.getResponses().get(0).getTextAnnotations();
        if (labels != null) {
            for (EntityAnnotation label : labels) {
                message.append(String.format(Locale.US, "%.3f: %s", label.getScore(), label.getDescription()));
                message.append("\n");
            }
        } else {
            message.append("nothing");
        }

        return message.toString();
    }
}
