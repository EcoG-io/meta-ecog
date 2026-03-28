# meta-ecog: EcoG OCPP Service Yocto Layer

Yocto meta-layer providing the **EcoG OCPP 2.0.1 Charging Station service** and all its Python dependencies, built from source and installed directly into the rootfs.

Compatible with **Yocto Scarthgap (5.0)**.

---

## What this layer provides

| Component | Description |
|-----------|-------------|
| `recipes-apps/ocpp-service` | OCPP charging station application, installed to `/opt/ocpppy/cs/`, managed by systemd |
| `recipes-devtools/python/` | All Python runtime dependencies built from source (cryptography, rpds-py, ocpp, asyncio-mqtt, sqlalchemy, etc.) |

All Python packages are installed into the **rootfs** as standard Yocto packages. There is no pip install, no virtualenv, and no bundle directory at runtime — the packages are part of the image.

---

## Usage

### 1. Add the layer

```bash
bitbake-layers add-layer path/to/meta-ecog
```

Or add manually to `bblayers.conf`:

```bitbake
BBLAYERS += "/path/to/meta-ecog"
```

### 2. Add to your image

In your image recipe (`.bb` file):

```bitbake
IMAGE_INSTALL:append = " ocpp-service"
```

This pulls in `ocpp-service` and all its `RDEPENDS` (Python packages) automatically.

### 3. Configure the service

The systemd unit has sensible defaults. The only deployment-specific variable is the CSMS WebSocket URL. Set it via a systemd drop-in on the device:

```bash
systemctl edit ocpp-service
```

```ini
[Service]
Environment="CSMS_URL_OVERRIDE=wss://your-csms-host/ws"
```

Or place a `.env` file at `/opt/ocpppy/cs/.env` — the application reads it automatically at startup.

**Default environment (already in the service unit):**

| Variable | Default | Description |
|----------|---------|-------------|
| `MQTT_HOST` | `localhost` | MQTT broker host |
| `MQTT_PORT` | `1883` | MQTT broker port |
| `CS_DEFAULT_HEARTBEAT_INTERVAL` | `60` | OCPP heartbeat interval (seconds) |

An MQTT broker (e.g. mosquitto) must be running on the device:

```bitbake
IMAGE_INSTALL:append = " ocpp-service mosquitto"
```

---

## Installation paths on target

| Path | Contents |
|------|----------|
| `/opt/ocpppy/cs/` | Application source and entry point (`run.py`) |
| `/lib/systemd/system/ocpp-service.service` | Systemd unit file |

Python packages are in the standard rootfs location (`/usr/lib/python3.x/site-packages/`), not in a separate bundle.

---

## Managing the service

```bash
systemctl start ocpp-service
systemctl stop ocpp-service
systemctl enable ocpp-service      # start on boot
journalctl -fu ocpp-service        # follow logs
```

---

## Python dependency version pinning

All Python packages are pinned to exact versions matching `requirements.txt`. Where the Yocto scarthgap base layer (`meta`) ships a conflicting version, this layer overrides it:

- Packages at a **higher version** in this layer automatically win (Yocto selects the highest available version).
- `python3-websockets` is explicitly pinned via `PREFERRED_VERSION` in `conf/layer.conf` because the scarthgap base ships a newer version (13.0.1) than required (12.0).
