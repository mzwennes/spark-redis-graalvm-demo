native-image \
    --class-path build/libs/ceres-inventory-all-deps-0.0.1.jar \
    -H:EnableURLProtocols=http \
    -H:IncludeResources="log4j.properties" \
    -H:Name=inventory \
    -H:Class=inventory.ApplicationKt \
    -H:+ReportUnsupportedElementsAtRuntime \
    -H:+AllowVMInspection
