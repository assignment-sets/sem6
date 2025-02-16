# Open Shortest Path Algorithm

## Network Setup (Based on Your College’s OSPF Config)

- **Router 1 (R1)** → **Switch 1** → **PC1, PC2** (10.0.0.0/8)
- **Router 2 (R2)** → **Switch 2** → **PC3, PC4** (30.0.0.0/8)
- **WAN link** between **R1 & R2** via **se2/0** (20.0.0.0/8) (use generic router and serial DCE cable)

### IP Address Plan

| Device | Interface | IP Address | Subnet Mask | Description |
|--------|-----------|------------|-------------|-------------|
| R1     | fa0/0     | 10.0.0.1   | 255.0.0.0   | LAN (PC1 & PC2) |
| R1     | se2/0     | 20.0.0.1   | 255.0.0.0   | WAN to R2 (DCE) |
| R2     | fa0/0     | 30.0.0.1   | 255.0.0.0   | LAN (PC3 & PC4) |
| R2     | se2/0     | 20.0.0.2   | 255.0.0.0   | WAN to R1 |
| PC1    | NIC       | 10.0.0.2   | 255.0.0.0   | Gateway: 10.0.0.1 |
| PC2    | NIC       | 10.0.0.3   | 255.0.0.0   | Gateway: 10.0.0.1 |
| PC3    | NIC       | 30.0.0.2   | 255.0.0.0   | Gateway: 30.0.0.1 |
| PC4    | NIC       | 30.0.0.3   | 255.0.0.0   | Gateway: 30.0.0.1 |

### Router 1 (R1) Configuration

```markdown
# Enter privileged mode
R1> en

# Enter global configuration mode
R1# conf t

# Enable OSPF with process ID 30
R1(config)# router ospf 30

# Configure OSPF for the LAN network
R1(config-router)# network 10.0.0.0 0.255.255.255 area 0

# Configure OSPF for the WAN network
R1(config-router)# network 20.0.0.0 0.255.255.255 area 0

# Exit router configuration mode
R1(config-router)# exit

# Exit global configuration mode
R1(config)# exit

# Save the configuration
R1# wr
```

### Router 2 (R2) Configuration

```markdown
# Enter privileged mode
R2> en

# Enter global configuration mode
R2# conf t

# Enable OSPF with process ID 30
R2(config)# router ospf 30

# Configure OSPF for the LAN network
R2(config-router)# network 30.0.0.0 0.255.255.255 area 0

# Configure OSPF for the WAN network
R2(config-router)# network 20.0.0.0 0.255.255.255 area 0

# Exit router configuration mode
R2(config-router)# exit

# Exit global configuration mode
R2(config)# exit

# Save the configuration
R2# wr
```

## Test

Ping across the network:
From PC1:
```bash
ping 30.0.0.2
```
If OSPF is working, the ping should succeed.