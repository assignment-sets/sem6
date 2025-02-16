# Basic Cisco Switch Configuration

This document provides a basic switch configuration using Cisco CLI commands in Cisco Packet Tracer or real Cisco devices.

## Enter Privileged EXEC Mode
To begin configuring the switch, enter privileged EXEC mode:
```bash
Switch> enable
```

## Enter Global Configuration Mode
To modify the switch settings, enter global configuration mode:
```bash
Switch# configure terminal
```

## Set Hostname
Change the default switch name to make it identifiable:
```bash
Switch(config)# hostname MySwitch
```

## Set Privileged EXEC Password
To secure access to privileged EXEC mode (enable mode), set a strong encrypted password:
```bash
Switch(config)# enable secret MySecurePassword
```
Replace `MySecurePassword` with a strong password.

## Secure Console Access
To prevent unauthorized direct access via the console port, set a console password:
```bash
Switch(config)# line console 0
Switch(config-line)# password ConsolePass
Switch(config-line)# login
```
Replace `ConsolePass` with your preferred password.

## Set a Message of the Day (MOTD)
Display a login warning message for security purposes:
```bash
Switch(config)# banner motd # Unauthorized access is prohibited. #
```
Modify the message inside `# #` as needed.

## Save Configuration
After completing the configuration, save it so that changes persist after reboot:
```bash
Switch# write memory
```

## Conclusion
This basic configuration secures the switch with a hostname, authentication, and a login banner. Make sure to use strong passwords and regularly update your security settings.
