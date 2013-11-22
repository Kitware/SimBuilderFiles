<?xml version="1.0"?>
<SMTK_AttributeManager Version="1">
  <!--**********  Category and Analysis Infomation ***********-->
  <Categories Default="General">
    <Cat>General</Cat>
    <Cat>Incompressible Navier-Stokes</Cat>
    <Cat>Energy Equation</Cat>
  </Categories>
  <Analyses>
    <Analysis Type="Incompressible Navier-Stokes Analysis">
      <Cat>General</Cat>
      <Cat>Incompressible Navier-Stokes</Cat>
    </Analysis>
    <Analysis Type="NS and Energy Equation Analysis"><!-- can compute in enthalpy form or temperature form of energy equation -->
      <Cat>General</Cat>
      <Cat>Incompressible Navier-Stokes</Cat>
      <Cat>Energy Equation</Cat>
    </Analysis>
  </Analyses>
  <!--**********  Attribute Definitions ***********-->
  <Definitions>
    <!--***  Problem definition  ***-->
    <AttDef Type="hydrostat" BaseType="" Version="0" Unique="true"> <!-- acbauer - this needs a node set id -->
      <ItemDefinitions>
        <Group Name="Hydrostat" Label="Hydrostatic pressure" NumberOfRequiredGroups="1">
          <ItemDefinitions>
            <Double Name="Value" Label="Load Curve" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1" Optional="true" IsEnabledByDefault="false">
              <DetailedDescription>Prescribe the hydrostatic pressure. This may be used in conjunction with prescribed pressure
              boundary conditions, or by itself. When used by itself, the hstat keyword plays two roles. It makes
              the pressure-Poisson equation non-singular and it permits the pressure for the system to be uniquely
              determined. When the hstat keyword is used with prescribed pressure boundary conditions, then
              it only specifies the unique hydrostatic pressure level for the system. In either case, the pressure
              time-history and field output is adjusted to reflect the specified hydrostatic pressure level.</DetailedDescription>
              <ExpressionType>PolyLinearFunction</ExpressionType>
              <Categories>
                <Cat>General</Cat>
              </Categories>
            </Double>
            <Double Name="Scale" Label="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
              <Categories>
                <Cat>General</Cat>
              </Categories>
            </Double>
          </ItemDefinitions>
        </Group>
      </ItemDefinitions>
    </AttDef>

    <!--***  Materials Definitions ***-->
    <AttDef Type="Material" Label="Material" BaseType="" Version="0" Unique="true" Associations="r">
      <ItemDefinitions>
        <Double Name="Density" Label="Density" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <DefaultValue>1.0</DefaultValue>
          <RangeInfo>
            <Min Inclusive="false">0</Min>
          </RangeInfo>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <!-- acbauer - may want to consider doing an enum type for Cp and Cv -->
        <Double Name="Cp" Label="Cp" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <BriefDescription>Constant pressure specific heat</BriefDescription>
          <RangeInfo>
            <Min Inclusive="true">0</Min>
          </RangeInfo>
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
        <Double Name="Cv" Label="Cv" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <BriefDescription>Constant volume specific heat</BriefDescription>
          <RangeInfo>
            <Min Inclusive="true">0</Min>
          </RangeInfo>
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
        <Double Name="k" Label="Thermal conductivity" Version="0" AdvanceLevel="0" NumberOfRequiredValues="6">
          <BriefDescription>Thermal conductivity tensor (symmetric)</BriefDescription>
          <ComponentLabels>
            <Label>XX</Label>
            <Label>XY</Label>
            <Label>XZ</Label>
            <Label>YY</Label>
            <Label>YZ</Label>
            <Label>ZZ</Label>
          </ComponentLabels>
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
        <Double Name="mu" Label="Mu" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <DefaultValue>1.0</DefaultValue>
          <BriefDescription>Molecular viscosity</BriefDescription>
          <RangeInfo>
            <Min Inclusive="false">0</Min>
          </RangeInfo>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="Tref" Label="Reference Temperature" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <DefaultValue>0.0</DefaultValue>
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
        <Double Name="beta" Label="Beta" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <DefaultValue>0.0</DefaultValue>
          <BriefDescription>Coefficient of thermal expansion</BriefDescription>
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>

    <!--*** Body force definitions ***-->
    <AttDef Type="BodyForce" BaseType="" Version="0" Unique="false" Associations="r"/>
    <AttDef Type="GravityForce" Label="Gravity Force" BaseType="BodyForce" Version="0" Unique="true" Associations="r">
      <ItemDefinitions>
        <Double Name="GravityForce" Label="Load Curve" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1" Optional="true" IsEnabledByDefault="false">
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Label="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="3">
          <ComponentLabels>
            <Label>X</Label>
            <Label>Y</Label>
            <Label>Z</Label>
          </ComponentLabels>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="BoussinesqForce" Label="Boussinesq Force" BaseType="BodyForce" Version="0" Unique="true" Associations="r">
      <ItemDefinitions>
        <Double Name="BoussinesqForce" Label="Load Curve" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1" Optional="true" IsEnabledByDefault="false">
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Energy Equation</Cat>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Label="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="3">
          <ComponentLabels>
            <Label>X</Label>
            <Label>Y</Label>
            <Label>Z</Label>
          </ComponentLabels>
          <Categories>
            <Cat>Energy Equation</Cat>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="HeatSource" Label="Heat Source" BaseType="BodyForce" Version="0" Unique="true" Associations="r">
      <ItemDefinitions>
        <Double Name="Heat Source" Label="Load Curve" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1" Optional="true" IsEnabledByDefault="false">
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Label="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>

    <!--***  Initial Condition Definitions ***-->
    <AttDef Type="InitialConditions" BaseType="" Version="0" Abstract="0">
      <ItemDefinitions>
        <Group Name="InitialConditions" Label="Initial Conditions" NumberOfRequiredGroups="1">
          <ItemDefinitions>
            <Double Name="Velocity" Label="Initial velocity" Version="0" NumberOfRequiredValues="3">
              <ComponentLabels>
                <Label>X</Label>
                <Label>Y</Label>
                <Label>Z</Label>
              </ComponentLabels>
              <DefaultValue>0</DefaultValue>
              <Categories>
                <Cat>Incompressible Navier-Stokes</Cat>
              </Categories>
            </Double>
            <Double Name="tke" Label="Initial turbulent kinetic energy" Version="0" NumberOfRequiredValues="1">
              <RangeInfo>
                <Min Inclusive="false">0</Min>
              </RangeInfo>
              <Categories>
                <Cat>Incompressible Navier-Stokes</Cat>
              </Categories>
            </Double>
            <Double Name="itdr" Label="Initial turbulent dissipation rate" Version="0" NumberOfRequiredValues="1">
              <RangeInfo>
                <Min Inclusive="false">0</Min>
              </RangeInfo>
              <Categories>
                <Cat>Incompressible Navier-Stokes</Cat>
              </Categories>
            </Double>
            <Double Name="idts" Label="Inverse dissipation time scale" Version="0" NumberOfRequiredValues="1">
              <RangeInfo>
                <Min Inclusive="false">0</Min>
              </RangeInfo>
              <Categories>
                <Cat>Incompressible Navier-Stokes</Cat>
              </Categories>
            </Double>
            <Double Name="tv" Label="Turbulent viscosity (Spalart-Allmaras and DES models)" Version="0" NumberOfRequiredValues="1">
              <RangeInfo>
                <Min Inclusive="false">0</Min>
              </RangeInfo>
              <Categories>
                <Cat>Incompressible Navier-Stokes</Cat>
              </Categories>
            </Double>
            <!-- acbauer - should be temperature or energy equation -->
            <Double Name="temperature" Label="Temperature" Version="0" NumberOfRequiredValues="1">
              <Categories>
                <Cat>Energy Equation</Cat>
              </Categories>
            </Double>
            <Double Name="internalenergy" Label="Internal Energy" Version="0" NumberOfRequiredValues="1">
              <Categories>
                <Cat>Energy Equation</Cat>
              </Categories>
            </Double>
            <Double Name="enthalpy" Label="Enthalpy" Version="0" NumberOfRequiredValues="1">
              <Categories>
                <Cat>Energy Equation</Cat>
              </Categories>
            </Double>
          </ItemDefinitions>
        </Group>
      </ItemDefinitions>
    </AttDef>

    <!--***  Boundary Condition Definitions ***-->
    <AttDef Type="BoundaryCondition" BaseType="" Abstract="1" Version="0" Unique="false" Associations="f" />
    <!-- BC structure to enforce uniqueness/prevent overspecification of BCs on a boundary -->
    <AttDef Type="EnthalpyBoundaryCondition" Label="Enthalpy" BaseType="BoundaryCondition" Abstract="0" Version="0" Unique="true" Associations="f">
      <ItemDefinitions>
        <Double Name="LoadCurve" Label="Load Curve" Version="0" NumberOfRequiredValues="1" AdvanceLevel="1" Optional="true" IsEnabledByDefault="false">
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Label="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="Turbulent Dissipation" Label="Turbulent Dissipation" BaseType="BoundaryCondition" Abstract="0" Version="0" Unique="true" Associations="f">
      <ItemDefinitions>
        <Double Name="LoadCurve" Version="0" Label="Load Curve" NumberOfRequiredValues="1" AdvanceLevel="1" Optional="true" IsEnabledByDefault="false">
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Label="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>
    <!-- both wall bc and penetration bc are written out as distancebc but wall won't have any inputs whereaas penetration will -->
    <AttDef Type="Wall" Lable="Wall" BaseType="BoundaryCondition" Abstract="0" Version="0" Unique="true" Associations="f">
      <ItemDefinitions>
        <Void Name="void" Label="Not used - do not show" Version="0" NumberOfRequiredValues="0" AdvanceLevel="1">
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Void>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="Penetration" Label="Penetration" BaseType="BoundaryCondition" Abstract="0" Version="0" Unique="true" Associations="f">
      <ItemDefinitions>
        <Double Name="LoadCurve" Label="Load Curve" Version="0" NumberOfRequiredValues="1" AdvanceLevel="1" Optional="true" IsEnabledByDefault="false">
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Label="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="Pressure" Label="Pressure" BaseType="BoundaryCondition" Abstract="0" Version="0" Unique="true" Associations="f">
      <ItemDefinitions>
        <Double Name="LoadCurve" Label="Load Curve" Version="0" NumberOfRequiredValues="1" AdvanceLevel="1" Optional="true" IsEnabledByDefault="false">
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Label="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="Temperature" Label="Temperature" BaseType="BoundaryCondition" Abstract="0" Version="0" Unique="true" Associations="f">
      <ItemDefinitions>
        <Double Name="LoadCurve" Label="Load Curve" Version="0" NumberOfRequiredValues="1" AdvanceLevel="1" Optional="true" IsEnabledByDefault="false">
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Label="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="TurbulentViscosity" Label="Turbulent Viscosity" BaseType="BoundaryCondition" Abstract="0" Version="0" Unique="true" Associations="f">
      <ItemDefinitions>
        <Double Name="LoadCurve" Label="Load Curve" Version="0" NumberOfRequiredValues="1" AdvanceLevel="1" Optional="true" IsEnabledByDefault="false">
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Label="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>

    <!--*** Velocity boundary conditions ***-->
    <AttDef Type="VelXBoundaryCondition" Label="X Velocity" BaseType="BoundaryCondition" Abstract="0" Version="0" Unique="true" Associations="f" >
      <ItemDefinitions>
        <Double Name="LoadCurve" Label="Load Curve" Version="0" NumberOfRequiredValues="1" AdvanceLevel="1" Optional="true" IsEnabledByDefault="false">
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Label="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="VelYBoundaryCondition" Label="Y Velocity" BaseType="BoundaryCondition" Abstract="0" Version="0" Unique="true" Associations="f" >
      <ItemDefinitions>
        <Double Name="LoadCurve" Label="Load Curve" Version="0" NumberOfRequiredValues="1" AdvanceLevel="1" Optional="true" IsEnabledByDefault="false">
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Label="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="VelZBoundaryCondition" Label="Z Velocity" BaseType="BoundaryCondition" Abstract="0" Version="0" Unique="true" Associations="f" >
      <ItemDefinitions>
        <Double Name="LoadCurve" Label="Load Curve" Version="0" NumberOfRequiredValues="1" AdvanceLevel="1" Optional="true" IsEnabledByDefault="false">
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Label="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="SymmetryVelXBoundaryCondition" Label="X Symmetry Velocity" BaseType="VelXBoundaryCondition" Abstract="0" Version="0" Unique="true" Associations="f" />
    <AttDef Type="SymmetryVelYBoundaryCondition" Label="Y Symmetry Velocity" BaseType="VelYBoundaryCondition" Abstract="0" Version="0" Unique="true" Associations="f" />
    <AttDef Type="SymmetryVelZBoundaryCondition" Label="Z Symmetry Velocity" BaseType="VelZBoundaryCondition" Abstract="0" Version="0" Unique="true" Associations="f" />

    <AttDef Type="HeatFlux" Label="Heat Flux" BaseType="BoundaryCondition" Abstract="1" Version="0" Unique="true" Associations="f" >
      <ItemDefinitions>
        <Double Name="LoadCurve" Label="Load Curve" Version="0" NumberOfRequiredValues="1" AdvanceLevel="1" Optional="true" IsEnabledByDefault="false">
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Label="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>

    <!--***  Execution Definitions ***-->
    <AttDef Type="LoadBalancer" BaseType="" Abstract="0" Version="0" Unique="true">
      <ItemDefinitions>
        <String Name="Method" Label="Load Balance Method" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
          <DiscreteInfo DefaultIndex="4">
            <Value Enum="RCB">rcb</Value>
            <Value Enum="RIB">rib</Value>
            <Value Enum="SFC">sfc</Value>
            <Value Enum="Hypergraph">hg</Value>
            <Value Enum="SFC and HG">sfc_and_hg</Value>
          </DiscreteInfo>
        </String>
        <Void Name="Load Balance Diagnostics" Label="Load Balance Diagnostics" Version="0" AdvanceLevel="1" Optional="true" IsEnabledByDefault="false" NumberOfRequiredValues="0">
          <BriefDescription>Load Balance Verbose Level</BriefDescription>
        </Void>
      </ItemDefinitions>
    </AttDef>

    <AttDef Type="ExecutionControl" BaseType="" Abstract="0" Version="0" Unique="true">
      <ItemDefinitions>
        <Int Name="ExecutionControl" Label="Execution Control Frequency Checking"  Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
          <BriefDescription>Frequency of checking execution control file</BriefDescription>
          <RangeInfo>
            <Min Inclusive="true">0</Min>
          </RangeInfo>
          <DefaultValue>0</DefaultValue>
        </Int>
      </ItemDefinitions>
    </AttDef>

    <AttDef Type="Output" BaseType="" Abstract="0" Version="0" Unique="false" Associations="">
      <ItemDefinitions>
        <Group Name="RestartOutput" Label="Restart Output" NumberOfRequiredGroups="1">
          <ItemDefinitions>
            <Int Name="frequency" Label="Frequency" Version="0" NumberOfRequiredValues="1">
              <BriefDescription>Output dump file frequency. 0 implies do not output.</BriefDescription>
              <RangeInfo>
                <Min Inclusive="true">0</Min>
              </RangeInfo>
              <DefaultValue>0</DefaultValue>
            </Int>
            <String Name="type" Label="Type" Version="0" NumberOfRequiredValues="1">
              <BriefDescription>Output dump format</BriefDescription>
              <DiscreteInfo DefaultIndex="0">
                <Value Enum="Serial">serial</Value>
                <Value Enum="Distributed">distributed</Value>
              </DiscreteInfo>
            </String>
          </ItemDefinitions>
        </Group>
        <Group Name="FieldOutput" Label="Field Output" NumberOfRequiredGroups="1">
          <ItemDefinitions>
            <Int Name="frequency" Label="Frequency" Version="0" NumberOfRequiredValues="1">
              <BriefDescription>Output dump file frequency. 0 implies do not output.</BriefDescription>
              <RangeInfo>
                <Min Inclusive="true">0</Min>
              </RangeInfo>
              <DefaultValue>20</DefaultValue>
            </Int>
            <String Name="type" Label="Type" Version="0" NumberOfRequiredValues="1">
              <BriefDescription>Output field format</BriefDescription>
              <DiscreteInfo DefaultIndex="4">
                <Value Enum="GMV ASCII">gmv_ascii</Value>
                <Value Enum="ExodusII">exodusii</Value>
                <Value Enum="ExodusII (64 bit)">exodusii64</Value>
                <Value Enum="ExodusII (HDF5)">exodusii_hdf5</Value>
                <Value Enum="ExodusII (64 bit HDF5)">exodusii64_hdf5</Value>
                <Value Enum="VTK ASCII">vtk_axcii</Value>
              </DiscreteInfo>
            </String>
          </ItemDefinitions>
        </Group>
      </ItemDefinitions>
    </AttDef>

    <AttDef Type="StatusInformation" BaseType="" Abstract="0" Version="0" Unique="true">
      <ItemDefinitions>
        <Int Name="minmaxfrequency" Label="Interval to report min/max velocity values" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
          <DefaultValue>10</DefaultValue>
          <RangeInfo>
            <Min Inclusive="yes">0</Min>
          </RangeInfo>
        </Int>
        <Int Name="tifrequency" Label="Time history write frequency" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
          <DefaultValue>1</DefaultValue>
          <RangeInfo>
            <Min Inclusive="yes">0</Min>
          </RangeInfo>
        </Int>
        <String Name="PrintLevel" Label="ASCII Print Level" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
          <BriefDescription>Controls the amount of data written to the ASCII (human-readable) output file</BriefDescription>
          <DiscreteInfo DefaultValue="0">
            <Value Enum="param">param</Value>
            <Value Enum="results">results</Value>
            <Value Enum="verbose">verbose</Value>
          </DiscreteInfo>
        </String>
        <Int Name="hcfrequency" Label="Interval (only used if ASCII Print Level is verbose)" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
          <DefaultValue>0</DefaultValue>
          <RangeInfo>
            <Min Inclusive="yes">0</Min>
          </RangeInfo>
        </Int>
      </ItemDefinitions>
    </AttDef>

    <AttDef Type="VarOutput" BaseType="" Abstract="1" Version="0" Unique="false" AdvanceLevel="0" NumberOfRequiredValues="1"/>
    <AttDef Type="HistVarOutput" BaseType="VarOutput" Abstract="0" Version="0" Unique="false">
      <ItemDefinitions>
        <Int Name="Id" Label="Id" Version="0" NumberOfRequiredValues="1">
          <RangeInfo>
            <Min Inlcusive="true">0</Min>
          </RangeInfo>
        </Int>
        <String Name="varname" Label="Variable Name" Version="0" NumberOfRequiredValues="1"/>
        <Int Name="type" Label="Type" Version="0" NumberOfRequiredValues="1">
          <DiscreteInfo>
            <Value Enum="Element">elem</Value>
            <Value Enum="Node">node</Value>
            <Value Enum="Side set">side</Value>
          </DiscreteInfo>
        </Int>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="NodeElemPlotVarOutput" BaseType="VarOutput" Abstract="0" Version="0" Unique="false">
      <ItemDefinitions>
        <String Name="varname" Label="Variable Name" Version="0" NumberOfRequiredValues="1"/>
        <Int Name="type" Label="Type" Version="0" NumberOfRequiredValues="1">
          <DiscreteInfo>
            <Value Enum="Element">elem</Value>
            <Value Enum="Node">node</Value>
          </DiscreteInfo>
        </Int>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="SideSetPlotVarOutput" BaseType="VarOutput" Abstract="0" Version="0" Unique="false">
      <ItemDefinitions>
        <Int Name="Id" Label="Id" Version="0" NumberOfRequiredValues="1">
          <RangeInfo>
            <Min Inlcusive="true">0</Min>
          </RangeInfo>
        </Int>
        <String Name="varname" Label="Variable Name" Version="0" NumberOfRequiredValues="1"/>
      </ItemDefinitions>
    </AttDef>

    <!--*** Temporal statistics options ***-->
    <AttDef Type="TempStatVarOutput" BaseType="" Abstract="1" Version="0" Unique="false"/>
    <AttDef Type="NodeElemTempStatVarOutput" Label="NodeElemTempStatVarOutput" BaseType="TempStatVarOutput" Abstract="0" Version="0" Unique="false">
      <ItemDefinitions>
        <String Name="varname" Label="Variable Name" Version="0" NumberOfRequiredValues="1"/>
        <Int Name="type" Label="Type" Version="0" NumberOfRequiredValues="1">
          <DiscreteInfo>
            <Value Enum="Element">elem</Value>
            <Value Enum="Node">node</Value>
          </DiscreteInfo>
        </Int>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="SideSetTempStatVarOutput" Label="SideSetTempStatVarOutput" BaseType="TempStatVarOutput" Abstract="0" Version="0" Unique="false">
      <ItemDefinitions>
        <Int Name="Id" Label="Id" Version="0" NumberOfRequiredValues="1">
          <RangeInfo>
            <Min Inclusive="true">0</Min>
          </RangeInfo>
        </Int>
        <String Name="varname" Label="Variable Name" Version="0" NumberOfRequiredValues="1"/>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="TempStatVarStatistics" Label="TempStatVarStatistics" BaseType="" Abstract="0" Version="0" Unique="true">
      <ItemDefinitions>
        <Group Name="TemporalStatistics" Label="Temporal Statistics" NumberOfRequiredGroups="1">
          <ItemDefinitions>
            <Double Name="StartTime" Label="Start Time" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
              <DefaultValue>0.0</DefaultValue>
            </Double>
            <Double Name="EndTime" Label="End Time" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
              <DefaultValue>1.0</DefaultValue>
            </Double>
            <Double Name="PlotWinSize" Label="Time Window Size" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
              <DefaultValue>0.1</DefaultValue>
              <RangeInfo>
                <Min Inclusive="false">0.0</Min>
              </RangeInfo>
            </Double>
          </ItemDefinitions>
        </Group>
      </ItemDefinitions>
    </AttDef>

    <!--*** Solver Definitions ***-->
    <AttDef Type="ppesolver" Label="ppesolver" BaseType="" Version="0" Unique="" Associations="">
      <ItemDefinitions>
        <Group Name="PressurePoissonSolver" Label="Pressure Poisson Solver" NumberOfRequiredGroups="1">
          <ItemDefinitions>
            <String Name="type" Label="Type" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <DiscreteInfo DefaultValue="0">
                <Value Enum="AMG">AMG</Value>
                <Value Enum="SSORCG">SSORCG</Value>
                <Value Enum="JPCG">JPCG</Value>
              </DiscreteInfo>
            </String>
            <Int Name="itmax" Label="Maximum number of iterations" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <BriefDescription>The maximum number of iterations. In the case of AMG, this is the maximum number of V or W cycles.</BriefDescription>
              <DefaultValue>500</DefaultValue>
              <RangeInfo>
                <Min Inclusive="false">0</Min>
                <Max Inclusive="true">500</Max>
              </RangeInfo>
            </Int>
            <Int Name="itchk" Label="Convergence criteria checking frequency" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <BriefDescription>The number of iterations to take before checking convergence criteria.</BriefDescription>
              <DefaultValue>2</DefaultValue>
              <RangeInfo>
                <Min Inclusive="false">0</Min>
                <Max Inclusive="true">25</Max>
              </RangeInfo>
            </Int>
            <Void Name="diagnostics" Label="Diagnostics" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1" Optional="true" IsEnabledByDefault="false">
              <BriefDescription>Enable/disable the diagnostic information from the linear solver.</BriefDescription>
            </Void>
            <Void Name="convergence" Label="Convergence metrics" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1" Optional="true" IsEnabledByDefault="false">
              <BriefDescription>Enable/disable the convergence metrics or the linear solver.</BriefDescription>
            </Void>
            <Double Name="eps" Label="Convergence criteria" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <DefaultValue>1e-05</DefaultValue>
              <RangeInfo>
                <Min Inclusive="false">0.0</Min>
                <Max Inclusive="true">1.0</Max>
              </RangeInfo>
            </Double>
            <Double Name="pivot" Label="Preconditioner zero pivot value" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <DefaultValue>1e-16</DefaultValue>
              <RangeInfo>
                <Min Inclusive="false">0.0</Min>
                <Max Inclusive="true">0.1</Max>
              </RangeInfo>
            </Double>
          </ItemDefinitions>
        </Group>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="momentumsolver" Label="Momentum Solver" BaseType="" Version="0">
      <ItemDefinitions>
        <Group Name="MomentumSolver" Label="Momentum Solver" NumberOfRequiredGroups="1">
          <ItemDefinitions>
            <String Name="type" Label="Type" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <DiscreteInfo DefaultValue="0">
                <Value Enum="Flexible GMRES">FGMRES</Value>
                <Value Enum="ILU-Preconditioned FGMRES">ILUFGMRES</Value>
                <Value Enum="GMRES">GMRES</Value>
                <Value Enum="ILU-Preconditioned GMRES">ILUGMRES</Value>
              </DiscreteInfo>
            </String>
            <Int Name="restart" Label="Number of restart vectors" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <DefaultValue>30</DefaultValue>
              <RangeInfo>
                <Min Inclusive="false">0</Min>
                <Max Inclusive="true">100</Max>
              </RangeInfo>
            </Int>
            <Int Name="itmax" Label="Maximum number of iterations" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <BriefDescription>The maximum number of iterations. In the case of AMG, this is the maximum number of V or W cycles.</BriefDescription>
              <DefaultValue>500</DefaultValue>
              <RangeInfo>
                <Min Inclusive="false">0</Min>
                <Max Inclusive="true">500</Max>
              </RangeInfo>
            </Int>
            <Int Name="itchk" Label="Convergence criteria checking frequency" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <BriefDescription>The number of iterations to take before checking convergence criteria.</BriefDescription>
              <DefaultValue>2</DefaultValue>
              <RangeInfo>
                <Min Inclusive="false">0</Min>
                <Max Inclusive="true">25</Max>
              </RangeInfo>
            </Int>
            <Void Name="diagnostics" Label="Diagnostics" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1" Optional="true" IsEnabledByDefault="false">
              <BriefDescription>Enable/disable the diagnostic information from the solver.</BriefDescription>
            </Void>
            <Void Name="convergence" Label="Convergence metrics" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1" Optional="true" IsEnabledByDefault="false">
              <BriefDescription>Enable/disable the convergence metrics or the solver.</BriefDescription>
            </Void>
            <Double Name="eps" Label="Convergence criteria" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <DefaultValue>1e-05</DefaultValue>
              <RangeInfo>
                <Min Inclusive="false">0.0</Min>
                <Max Inclusive="true">1.0</Max>
              </RangeInfo>
            </Double>
          </ItemDefinitions>
        </Group>
      </ItemDefinitions>
    </AttDef>
    <!--*** The transport solver is the same as the momentum solver but i copy it so that I can set the group label properly***-->
    <AttDef Type="transportsolver" Label="Transport Solver" BaseType="" Version="0">
      <ItemDefinitions>
        <Group Name="TransportSolver" Label="Transport Solver" NumberOfRequiredGroups="1">
          <ItemDefinitions>
            <String Name="type" Label="Type" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <DiscreteInfo DefaultValue="0">
                <Value Enum="Flexible GMRES">FGMRES</Value>
                <Value Enum="ILU-Preconditioned FGMRES">ILUFGMRES</Value>
                <Value Enum="GMRES">GMRES</Value>
                <Value Enum="ILU-Preconditioned GMRES">ILUGMRES</Value>
              </DiscreteInfo>
            </String>
            <Int Name="restart" Label="Number of restart vectors" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <DefaultValue>30</DefaultValue>
              <RangeInfo>
                <Min Inclusive="false">0</Min>
                <Max Inclusive="true">100</Max>
              </RangeInfo>
            </Int>
            <Int Name="itmax" Label="Maximum number of iterations" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <BriefDescription>The maximum number of iterations. In the case of AMG, this is the maximum number of V or W cycles.</BriefDescription>
              <DefaultValue>500</DefaultValue>
              <RangeInfo>
                <Min Inclusive="false">0</Min>
                <Max Inclusive="true">500</Max>
              </RangeInfo>
            </Int>
            <Int Name="itchk" Label="Convergence criteria checking frequency" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <BriefDescription>The number of iterations to take before checking convergence criteria.</BriefDescription>
              <DefaultValue>2</DefaultValue>
              <RangeInfo>
                <Min Inclusive="false">0</Min>
                <Max Inclusive="true">25</Max>
              </RangeInfo>
            </Int>
            <Void Name="diagnostics" Label="Diagnostics" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1" Optional="true" IsEnabledByDefault="false">
              <BriefDescription>Enable/disable the diagnostic information from the solver.</BriefDescription>
            </Void>
            <Void Name="convergence" Label="Convergence metrics" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1" Optional="true" IsEnabledByDefault="false">
              <BriefDescription>Enable/disable the convergence metrics or the solver.</BriefDescription>
            </Void>
            <Double Name="eps" Label="Convergence criteria" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <DefaultValue>1e-05</DefaultValue>
              <RangeInfo>
                <Min Inclusive="false">0.0</Min>
                <Max Inclusive="true">1.0</Max>
              </RangeInfo>
            </Double>
          </ItemDefinitions>
        </Group>
      </ItemDefinitions>

    </AttDef>

    <!--*** Solution method ***-->
    <AttDef Type="solution_method" BaseType="" Version="0">
      <ItemDefinitions>
        <Group Name="SolutionMethod" Label="Solution Method" NumberOfRequiredGroups="1">
          <ItemDefinitions>
            <String Name="strategy" Label="Strategy" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <DiscreteInfo DefaultValue="0">
                <Value Enum="Projection">projection</Value>
                <Value Enum="Picard">picard</Value>
              </DiscreteInfo>
            </String>
            <String Name="pressure_update" Label="Pressure Update" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <DiscreteInfo DefaultValue="1">
                <Value Enum="Chorin">chorin</Value>
                <Value Enum="Gradient">gradient</Value>
                <Value Enum="Curvature">curvature</Value>
              </DiscreteInfo>
            </String>
            <Void Name="curlfree_fix" Label="Curl Free Fix (Picard only)" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1" Optional="true" IsEnabledByDefault="false">
              <BriefDescription>Enable/disable the diagnostic information from the solver.</BriefDescription>
            </Void>
            <Int Name="itmax" Label="Maximum number of iterations" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <BriefDescription>The maximum number of non-linear iterations to be taken during each time step.</BriefDescription>
              <DefaultValue>5</DefaultValue>
              <RangeInfo>
                <Min Inclusive="false">0</Min>
              </RangeInfo>
            </Int>
            <Double Name="eps" Label="Convergence criteria" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <DefaultValue>1e-04</DefaultValue>
              <RangeInfo>
                <Min Inclusive="false">0.0</Min>
                <Max Inclusive="true">1.0</Max>
              </RangeInfo>
            </Double>
            <Double Name="eps_dist" Label="Convergence criteria for normal-distance function" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <DefaultValue>1e-05</DefaultValue>
              <RangeInfo>
                <Min Inclusive="false">0.0</Min>
                <Max Inclusive="true">1.0</Max>
              </RangeInfo>
            </Double>
            <Double Name="eps_p0" Label="Convergence criteria for initial div-free projection and initial pressure computation" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <DefaultValue>1e-05</DefaultValue>
              <RangeInfo>
                <Min Inclusive="false">0.0</Min>
                <Max Inclusive="true">1.0</Max>
              </RangeInfo>
            </Double>
            <Double Name="velrelax" Label="Velocity update under-relaxation parameter" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <DefaultValue>1.0</DefaultValue>
              <RangeInfo>
                <Min Inclusive="true">0.0</Min>
                <Max Inclusive="true">1.0</Max>
              </RangeInfo>
            </Double>
            <Double Name="enerelax" Label="Energy update under-relaxation parameter" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <DefaultValue>1.0</DefaultValue>
              <RangeInfo>
                <Min Inclusive="true">0.0</Min>
                <Max Inclusive="true">1.0</Max>
              </RangeInfo>
            </Double>
            <Double Name="nutrelax" Label="Spalart-Allmaras under-relaxation parameter" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <DefaultValue>1.0</DefaultValue>
              <RangeInfo>
                <Min Inclusive="true">0.0</Min>
                <Max Inclusive="true">1.0</Max>
              </RangeInfo>
            </Double>
            <Double Name="tkerelax" Label="Turbulent kinetic energy under-relaxation parameter" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <DefaultValue>1.0</DefaultValue>
              <RangeInfo>
                <Min Inclusive="true">0.0</Min>
                <Max Inclusive="true">1.0</Max>
              </RangeInfo>
            </Double>
            <Double Name="epsrelax" Label="Turbulent dissipation rate under-relaxation parameter" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <DefaultValue>1.0</DefaultValue>
              <RangeInfo>
                <Min Inclusive="true">0.0</Min>
                <Max Inclusive="true">1.0</Max>
              </RangeInfo>
            </Double>
            <Void Name="convergence" Label="Convergence" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1" Optional="true" IsEnabledByDefault="false">
              <BriefDescription>Enable/disable writing information about the non-linear convergence history to the conv file when using the Picard solution method.</BriefDescription>
            </Void>
            <Void Name="diagnostics" Label="Diagnostics" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1" Optional="true" IsEnabledByDefault="false">
              <BriefDescription>Enable/disable printing diagnostic information to the screen about the non-linear convergence history to the conv file when using the Picard solution method.</BriefDescription>
            </Void>
          </ItemDefinitions>
        </Group>
      </ItemDefinitions>
    </AttDef>

    <!--*** Time information ***-->
    <AttDef Type="simulationtime" BasetType="" Version="0">
      <ItemDefinitions>
        <Int Name="nsteps" Label="Maximum number of time steps" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <BriefDescription>The maximum number of time steps to be taken during a single simulation.</BriefDescription>
          <DefaultValue>10</DefaultValue>
          <RangeInfo>
            <Min Inclusive="true">1</Min>
            <Min Inclusive="true">10e9</Min>
          </RangeInfo>
          <DefaultValue>0</DefaultValue>
        </Int>
        <Double Name="term" Label="Simulation termination time" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <BriefDescription>Define the simulation termination time, in units consistent with the problem definition.</BriefDescription>
          <DefaultValue>1</DefaultValue>
          <RangeInfo>
            <Min Inclusive="false">0</Min>
          </RangeInfo>
          <DefaultValue>0</DefaultValue>
        </Double>
        <Double Name="deltat" Label="Time step size" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <BriefDescription>Define the time step size to be used. This value may be over-ridden by physics specific constraints on the time step.</BriefDescription>
          <DefaultValue>0.01</DefaultValue>
          <RangeInfo>
            <Min Inclusive="false">0</Min>
          </RangeInfo>
          <DefaultValue>0</DefaultValue>
        </Double>
      </ItemDefinitions>
    </AttDef>

    <!--*** Time integration  method ***-->
    <AttDef Type="time_integration" BaseType="" Version="0">
      <ItemDefinitions>
        <Group Name="TimeIntegration" Label="Time Integration" NumberOfRequiredGroups="1">
          <ItemDefinitions>
            <String Name="type" Label="Method" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <DiscreteInfo DefaultValue="0">
                <Value Enum="Fixed CFL">fixed_cfl</Value>
                <Value Enum="Fixed time step">fixed_dt</Value>
              </DiscreteInfo>
            </String>
            <Double Name="thetaa" Label="Time weight for advective terms" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <BriefDescription>Value of 0 indicates explicit advection and value of 1 indicates implicit</BriefDescription>
              <DefaultValue>0.5</DefaultValue>
              <RangeInfo>
                <Min Inclusive="true">0.0</Min>
                <Max Inclusive="true">1.0</Max>
              </RangeInfo>
            </Double>
            <Double Name="thetaK" Label="Time weight for advective terms" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <BriefDescription>Value of 0 indicates explicit advection and value of 1 indicates implicit</BriefDescription>
              <DefaultValue>0.5</DefaultValue>
              <RangeInfo>
                <Min Inclusive="true">0.0</Min>
                <Max Inclusive="true">1.0</Max>
              </RangeInfo>
            </Double>
            <Double Name="thetafF" Label="Time weight for source terms" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <BriefDescription>Value of 0 indicates explicit treatment and value of 1 indicates implicit</BriefDescription>
              <DefaultValue>0.5</DefaultValue>
              <RangeInfo>
                <Min Inclusive="true">0.0</Min>
                <Max Inclusive="true">1.0</Max>
              </RangeInfo>
            </Double>
          </ItemDefinitions>
        </Group>
      </ItemDefinitions>
    </AttDef>

    <!--*** Turbulence models ***-->
    <AttDef Type="Turbulence model" BaseType="" Abstract="1" Version="0"/>
    <AttDef Type="RNG k-e" Label="RNG k-e" BaseType="Turbulence model" Abstract="0" Version="0">
      <ItemDefinitions>
        <Void Name="timescale_limiter" Label="Time scale limiter" Version="0" AdvanceLevel="1" Optional="true" IsEnabledByDefault="false" NumberOfRequiredValues="0">
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Void>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="Smagorinsky" Label="Smagorinsky" BaseType="Turbulence model" Abstract="0" Version="0">
      <ItemDefinitions>
        <Double Name="c_s" Label="Constant" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
          <BriefDescription>Smagorinsky model constant.</BriefDescription>
          <DefaultValue>0.18</DefaultValue>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="prandtl" Label="Turbulent Prandtl number" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
          <DefaultValue>0.889</DefaultValue>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="schmidt" Label="Turbulent Schmidt number" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
          <DefaultValue>1.0</DefaultValue>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="WALE" Label="WALE" BaseType="Turbulence model" Abstract="0" Version="0">
      <ItemDefinitions>
        <Double Name="c_w" Label="Constant" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
          <BriefDescription>WALE model constant.</BriefDescription>
          <DefaultValue>0.18</DefaultValue>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="prandtl" Label="Turbulent Prandtl number" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
          <DefaultValue>0.889</DefaultValue>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="schmidt" Label="Turbulent Schmidt number" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
          <DefaultValue>1.0</DefaultValue>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>



    <!--***  Expression Definitions ***-->
    <!--    <AttDef Type="SimExpression" Abstract="1" Association="None"/> -->
    <AttDef Type="SimExpression" Abstract="1"/>
    <AttDef Type="SimInterpolation" BaseType="SimExpression" Abstract="1"/>
    <AttDef Type="PolyLinearFunction" Label="Expression" BaseType="SimInterpolation" Version="0" Unique="true" Associations="">
      <ItemDefinitions>
        <Group Name="ValuePairs" Label="Value Pairs" NumberOfRequiredGroups="1">
          <ItemDefinitions>
            <Double Name="X" Version="0" AdvanceLevel="0" NumberOfRequiredValues="0"/>
            <Double Name="Value" Version="0" AdvanceLevel="0" NumberOfRequiredValues="0"/>
          </ItemDefinitions>
        </Group>
        <!-- acbauer check on name here -->
        <String Name="Sim1DLinearExp" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1" />
      </ItemDefinitions>
    </AttDef>


  </Definitions>



  <!--**********  Attribute Instances ***********-->
  <Attributes>
  </Attributes>

  <!--********** Workflow Views ***********-->
  <RootView Title="SimBuilder">
    <DefaultColor R="1" G="1" B="0.5" />
    <InvalidColor R="1" G="0.5" B="0.5" />
    <AdvancedFontEffects Bold="0" Italic="1" />

    <AttributeView Title="Materials" ModelEntityFilter="r">
      <AttributeTypes>
        <Type>Material</Type>
      </AttributeTypes>
    </AttributeView>

    <AttributeView Title="Source Terms" ModelEntityFilter="r">
      <AttributeTypes>
        <Type>BodyForce</Type>
      </AttributeTypes>
    </AttributeView>

    <AttributeView Title="Turbulence">
      <AttributeTypes>
        <Type>Turbulence model</Type>
      </AttributeTypes>
    </AttributeView>

    <InstancedView Title="Execution">
      <InstancedAttributes>
        <Att Type="LoadBalancer">Load Balancer</Att>
        <Att Type="ExecutionControl">Execution Control</Att>
        <Att Type="Output">Output</Att>
        <Att Type="StatusInformation">Status Information</Att>
      </InstancedAttributes>
    </InstancedView>

    <InstancedView Title="Problem Definition">
      <InstancedAttributes>
        <Att Type="simulationtime">Simulation Time</Att>
        <Att Type="solution_method">Solution Method</Att>
        <Att Type="time_integration">Time Integration</Att>
        <!-- acbauer - put turbulence stuff here when ready -->
        <Att Type="hydrostat">Hydrostatic Pressure</Att>
        <Att Type="InitialConditions">Initial Conditions</Att>
      </InstancedAttributes>
    </InstancedView>

    <InstancedView Title="Solvers">
      <InstancedAttributes>
        <Att Type="ppesolver">Pressure Poisson Solver</Att>
        <Att Type="momentumsolver">Momentum Solver</Att>
        <Att Type="transportsolver">Transport Solver</Att>
      </InstancedAttributes>
    </InstancedView>

    <!--    <AttributeView Title="Field output" ModelEntityFilter="" > -->
    <AttributeView Title="Field output">
      <AttributeTypes>
        <Type>VarOutput</Type>
      </AttributeTypes>
    </AttributeView>

    <!-- acbauer - want to change this from a tabbed view to a tiled view -->
    <GroupView Title="Group Temporal Statistics">
      <InstancedView Title="Plot Window Parameters">
        <InstancedAttributes>
          <Att Type="TempStatVarStatistics">Parameters</Att>
        </InstancedAttributes>
      </InstancedView>
      <AttributeView Title="Variables">
        <AttributeTypes>
          <Type>TempStatVarOutput</Type>
        </AttributeTypes>
      </AttributeView>
    </GroupView>

    <AttributeView Title="Boundary Conditions" ModelEntityFilter="f">
      <AttributeTypes>
        <Type>BoundaryCondition</Type>
      </AttributeTypes>
    </AttributeView>

    <SimpleExpressionView Title="Functions">
      <Definition>PolyLinearFunction</Definition>
    </SimpleExpressionView>

  </RootView>
</SMTK_AttributeManager>
