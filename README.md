# meta-ecog: OCPP Service Layer

This Yocto layer provides the **EcoG OCPP Service**, a Python-based implementation for EV charging communication. It is designed to bridge modern Python development workflows (Poetry/Pip) with the Yocto/OpenEmbedded build system.


1. Installation Paths
The package is installed in an isolated directory structure to ensure system stability and avoid conflicts with the standard Yocto Python environment:

| Component | Target Path | Description |
| :--- | :--- | :--- |
| **Application Source** | `/opt/ocpppy/cs/` | [cite_start]Core service logic and entry point (`run.py`).  |
| **Dependency Bundle** | `/opt/ocpppy/bundle/` | [cite_start]Isolated Python site-packages and binary extensions.  |
| **Systemd Unit** | `/lib/systemd/system/` | [cite_start]Service management file (`ocpp-service.service`).  |


2. Setup and Integration

Add the `meta-ecog` layer to your BitBake build environment:

bitbake-layers add-layer path/to/meta-ecog

3. Service Configuration

The service is pre-configured via the ocpp-service.service file to utilize the isolated bundle. This ensures the application uses the specific versions of libraries (like cryptography and rpds-py) bundled during the build process.

Key Environment Variables:

    PYTHONPATH: /opt/ocpppy/bundle/lib/python3.12/site-packages

    WorkingDirectory: /opt/ocpppy/cs

    Default MQTT Host: localhost 

4. Technical Implementation Details

Architecture-Specific Binaries

The recipe dynamically detects the TARGET_ARCH to ensure that compiled Python extensions match the target hardware:

    aarch64 (e.g., i.MX93): Maps to manylinux2014_aarch64.

    arm (e.g., v7l): Maps to linux_armv7l.

    5. Usage and Troubleshooting
Managing the Service

Start the service:


systemctl start ocpp-service

Monitor logs:


journalctl -u ocpp-service -f