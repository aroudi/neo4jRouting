JAXB code generation documentation
==================================

The purpose of the bindings file bindings.xjb is to ensure that only
one copy of each of the classes from RTTA-Reference-v0.2.xsd is generated.

If bindings.xjb is not used as specified, copies of the classes
generated from xsd imports of RTTA-Reference-v0.2.xsd will end up in
multiple packages.     

Ordered steps for generating JAXB code:

If the an xsd has changed, it is a good idea to delete all of the
existing generated files in the target package before proceeding. 

Right click on the xsd file and...

1. RTTA-Reference-v0.2.xsd
Generate->JAXB Classes...->rtta-topology-api:
    Package: au.gov.nsw.railcorp.rtta.api.reference.data.generated

2. RTTA-Topology-v0.2.xsd
Generate->JAXB Classes...->rtta-topology-api:
    Package: au.gov.nsw.railcorp.rtta.api.topology.data.generated
    Bindings files: au.gov.nsw.railcorp.rtta.api.xsd.reference.bindings.xjb

3. RTTA-Network-v0.2.xsd
Generate->JAXB Classes...->rtta-topology-api:
    Package: au.gov.nsw.railcorp.rtta.api.network.data.generated
    Bindings files: au.gov.nsw.railcorp.rtta.api.xsd.reference.bindings.xjb

4. RTTA-Journey-v0.2.xsd
Generate->JAXB Classes...->rtta-topology-api:
    Package: au.gov.nsw.railcorp.rtta.api.journeytoline.data.generated
