# Router on a stick VLAN config

| PC  | VLAN   | IP Address    | Subnet Mask     | Gateway       |
|-----|--------|---------------|-----------------|---------------|
| PC1 | VLAN 10| 192.168.10.2  | 255.255.255.0   | 192.168.10.1  |
| PC2 | VLAN 10| 192.168.10.3  | 255.255.255.0   | 192.168.10.1  |
| PC3 | VLAN 20| 192.168.20.2  | 255.255.255.0   | 192.168.20.1  |
| PC4 | VLAN 20| 192.168.20.3  | 255.255.255.0   | 192.168.20.1  |

### 📌 Network Setup
- **VLAN 10 (Name: Sales)** → PC1, PC2
- **VLAN 20 (Name: HR)** → PC3, PC4
- **Switch** (One Trunk Port to Router)
- **Router** (Using sub-interfaces for VLANs)

### Step 1: Configure the Switch
```plaintext
Switch> en
Switch# conf t

# Create VLANs
Switch(config)# vlan 10
Switch(config-vlan)# name Sales
Switch(config-vlan)# vlan 20
Switch(config-vlan)# name HR
Switch(config-vlan)# exit

# Assign ports to VLANs
Switch(config)# int fa0/1
Switch(config-if)# switchport mode access
Switch(config-if)# switchport access vlan 10
Switch(config-if)# exit

Switch(config)# int fa0/2
Switch(config-if)# switchport mode access
Switch(config-if)# switchport access vlan 10
Switch(config-if)# exit

Switch(config)# int fa0/3
Switch(config-if)# switchport mode access
Switch(config-if)# switchport access vlan 20
Switch(config-if)# exit

Switch(config)# int fa0/4
Switch(config-if)# switchport mode access
Switch(config-if)# switchport access vlan 20
Switch(config-if)# exit

# Set trunk port for router
Switch(config)# int fa0/5
Switch(config-if)# switchport mode trunk
Switch(config-if)# exit

# Save config
Switch# copy run start
```

### Step 2: Configure the Router (Router-on-a-Stick)
```plaintext
Router> en
Router# conf t

# Create sub-interfaces for VLANs
Router(config)# int fa0/0
Router(config-if)# no shut
Router(config-if)# exit

Router(config)# int fa0/0.10
Router(config-subif)# encapsulation dot1Q 10
Router(config-subif)# ip add 192.168.10.1 255.255.255.0
Router(config-subif)# exit

Router(config)# int fa0/0.20
Router(config-subif)# encapsulation dot1Q 20
Router(config-subif)# ip add 192.168.20.1 255.255.255.0
Router(config-subif)# exit

# Save config
Router# copy running-config startup-config
```

> **Note:** Ensure that the switch and router configurations are saved to avoid losing changes after a reboot.
