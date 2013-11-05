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
      <DetailedDescription>??????Prescribe the hydrostatic pressure. This may be used in conjunction with prescribed pressure
      boundary conditions, or by itself. When used by itself, the hstat keyword plays two roles. It makes
      the pressure-Poisson equation non-singular and it permits the pressure for the system to be uniquely
      determined. When the hstat keyword is used with prescribed pressure boundary conditions, then
      it only specifies the unique hydrostatic pressure level for the system. In either case, the pressure
      time-history and field output is adjusted to reflect the specified hydrostatic pressure level.</DetailedDescription>
      <BriefDescription>??????Prescribe the hydrostatic pressure.</BriefDescription>
      <ItemDefinitions>
        <Double Name="Value" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1" Optional="true" IsEnabledByDefault="false">
          <Labels CommonLabel="Hydrostatic Pressure" />
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
        <Double Name="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Scale" />
          <Categories>
            <Cat>General</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>

    <!--***  Materials Definitions ***-->
    <AttDef Type="Material" BaseType="" Version="0" Unique="true" Associations="r">
      <ItemDefinitions>
        <Double Name="Density" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Density"/>
          <DefaultValue>1.0</DefaultValue>
          <RangeInfo>
            <Min Inclusive="false">0</Min>
          </RangeInfo>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <!-- may want to consider doing an enum type for Cp and Cv -->
        <Double Name="Cp" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Cp" />
          <BriefDescription>Constant pressure specific heat</BriefDescription>
          <RangeInfo>
            <Min Inclusive="true">0</Min>
          </RangeInfo>
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
        <Double Name="Cv" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Cv" />
          <BriefDescription>Constant volume specific heat</BriefDescription>
          <RangeInfo>
            <Min Inclusive="true">0</Min>
          </RangeInfo>
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
        <Double Name="k" Version="0" AdvanceLevel="0" NumberOfRequiredValues="6">
          <Labels CommonLabel="Thermal conductivity" />
          <BriefDescription>Thermal conductivity tensor (symmetric)</BriefDescription>
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
        <Double Name="mu" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Mu"/>
          <DefaultValue>1.0</DefaultValue>
          <BriefDescription>Molecular viscosity</BriefDescription>
          <RangeInfo>
            <Min Inclusive="false">0</Min>
          </RangeInfo>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="Tref" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Reference Temperature"/>
          <DefaultValue>0.0</DefaultValue>
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
        <Double Name="beta" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Beta"/>
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
    <AttDef Type="GravityForce" BaseType="BodyForce" Version="0" Unique="true" Associations="r">
      <ItemDefinitions>
        <Double Name="Gravity Force" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1" Optional="true" IsEnabledByDefault="false">
          <Labels CommonLabel="Load Curve"/>
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="3">
          <Labels CommonLabel="Scale" />
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="Boussinesq Force" BaseType="BodyForce" Version="0" Unique="true" Associations="r">
      <ItemDefinitions>
        <Double Name="BoussinesqForce" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1" Optional="true" IsEnabledByDefault="false">
          <Labels CommonLabel="Load Curve"/>
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="3">
          <Labels CommonLabel="Scale" />
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="Heat Source" BaseType="BodyForce" Version="0" Unique="true" Associations="r">
      <ItemDefinitions>
        <Double Name="Heat Source" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1" Optional="true" IsEnabledByDefault="false">
          <Labels CommonLabel="Heat Source"/>
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Scale" />
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>

    <!--***  Initial Condition Definitions ***-->
    <AttDef Type="InitialConditions" BaseType="" Version="0" Abstract="0">
      <ItemDefinitions>
        <Group Name="Initial Conditions" NumberOfRequiredValues="1">
          <ItemDefinitions>
            <Double Name="Velocity" Version="0" NumberOfRequiredValues="3">
              <Labels CommonLabel="Initial velocity" />
              <DefaultValue>0</DefaultValue>
              <Categories>
                <Cat>Incompressible Navier-Stokes</Cat>
              </Categories>
            </Double>
            <Double Name="tke" Version="0" NumberOfRequiredValues="1">
              <Labels CommonLabel="Initial turbulent kinetic energy" />
              <RangeInfo>
                <Min Inclusive="false">0</Min>
              </RangeInfo>
              <Categories>
                <Cat>Incompressible Navier-Stokes</Cat>
              </Categories>
            </Double>
            <Double Name="itdr" Version="0" NumberOfRequiredValues="1">
              <Labels CommonLabel="Initial turbulent dissipation rate" />
              <RangeInfo>
                <Min Inclusive="false">0</Min>
              </RangeInfo>
              <Categories>
                <Cat>Incompressible Navier-Stokes</Cat>
              </Categories>
            </Double>
            <Double Name="idts" Version="0" NumberOfRequiredValues="1">
              <Labels CommonLabel="Inverse dissipation time scale" />
              <RangeInfo>
                <Min Inclusive="false">0</Min>
              </RangeInfo>
              <Categories>
                <Cat>Incompressible Navier-Stokes</Cat>
              </Categories>
            </Double>
            <Double Name="tv" Version="0" NumberOfRequiredValues="1">
              <Labels CommonLabel="Turbulent viscosity (Spalart-Allmaras and DES models)" />
              <RangeInfo>
                <Min Inclusive="false">0</Min>
              </RangeInfo>
              <Categories>
                <Cat>Incompressible Navier-Stokes</Cat>
              </Categories>
            </Double>
            <!-- acbauer - should be temperature or energy equation -->
            <Double Name="temperature" Version="0" NumberOfRequiredValues="1">
              <Labels CommonLabel="Temperature" />
              <Categories>
                <Cat>Energy Equation</Cat>
              </Categories>
            </Double>
            <Double Name="internalenergy" Version="0" NumberOfRequiredValues="1">
              <Labels CommonLabel="Internal Energy" />
              <Categories>
                <Cat>Energy Equation</Cat>
              </Categories>
            </Double>
            <Double Name="enthalpy" Version="0" NumberOfRequiredValues="1">
              <Labels CommonLabel="Enthalpy" />
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
    <AttDef Type="EnthalpyBoundaryCondition" BaseType="BoundaryCondition" Abstract="0" Version="0" Unique="true" Associations="f">
      <ItemDefinitions>
        <Double Name="LoadCurve" Version="0" NumberOfRequiredValues="1" AdvanceLevel="1" Optional="true" IsEnabledByDefault="false">
          <Labels CommonLabel="Load Curve" />
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Scale" />
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="Turbulent Dissipation" BaseType="BoundaryCondition" Abstract="0" Version="0" Unique="true" Associations="f">
      <ItemDefinitions>
        <Double Name="LoadCurve" Version="0" NumberOfRequiredValues="1" AdvanceLevel="1" Optional="true" IsEnabledByDefault="false">
          <Labels CommonLabel="Load Curve" />
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Scale" />
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>
    <!-- both wall bc and penetration bc are written out as distancebc but wall won't have any inputs whereaas penetration will -->
    <AttDef Type="Wall" BaseType="BoundaryCondition" Abstract="0" Version="0" Unique="true" Associations="f"/>
    <AttDef Type="Penetration" BaseType="BoundaryCondition" Abstract="0" Version="0" Unique="true" Associations="f">
      <ItemDefinitions>
        <Double Name="LoadCurve" Version="0" NumberOfRequiredValues="1" AdvanceLevel="1" Optional="true" IsEnabledByDefault="false">
          <Labels CommonLabel="Load Curve" />
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Scale" />
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="Pressure" BaseType="BoundaryCondition" Abstract="0" Version="0" Unique="true" Associations="f">
      <ItemDefinitions>
        <Double Name="LoadCurve" Version="0" NumberOfRequiredValues="1" AdvanceLevel="1" Optional="true" IsEnabledByDefault="false">
          <Labels CommonLabel="Load Curve" />
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Scale" />
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="Temperature" BaseType="BoundaryCondition" Abstract="0" Version="0" Unique="true" Associations="f">
      <ItemDefinitions>
        <Double Name="LoadCurve" Version="0" NumberOfRequiredValues="1" AdvanceLevel="1" Optional="true" IsEnabledByDefault="false">
          <Labels CommonLabel="Load Curve" />
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Scale" />
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="Turbulent Viscosity" BaseType="BoundaryCondition" Abstract="0" Version="0" Unique="true" Associations="f">
      <ItemDefinitions>
        <Double Name="LoadCurve" Version="0" NumberOfRequiredValues="1" AdvanceLevel="1" Optional="true" IsEnabledByDefault="false">
          <Labels CommonLabel="Load Curve" />
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Scale" />
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>

    <!--*** Velocity boundary conditions ***-->
    <AttDef Type="VelXBoundaryCondition" BaseType="BoundaryCondition" Abstract="1" Version="0" Unique="true" Associations="f" >
      <ItemDefinitions>
        <Double Name="LoadCurve" Version="0" NumberOfRequiredValues="1" AdvanceLevel="1" Optional="true" IsEnabledByDefault="false">
          <Labels CommonLabel="Load Curve" />
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Scale" />
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="VelYBoundaryCondition" BaseType="BoundaryCondition" Abstract="1" Version="0" Unique="true" Associations="f" >
      <ItemDefinitions>
        <Double Name="LoadCurve" Version="0" NumberOfRequiredValues="1" AdvanceLevel="1" Optional="true" IsEnabledByDefault="false">
          <Labels CommonLabel="Load Curve" />
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Scale" />
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="VelZBoundaryCondition" BaseType="BoundaryCondition" Abstract="1" Version="0" Unique="true" Associations="f" >
      <ItemDefinitions>
        <Double Name="LoadCurve" Version="0" NumberOfRequiredValues="1" AdvanceLevel="1" Optional="true" IsEnabledByDefault="false">
          <Labels CommonLabel="Load Curve" />
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Scale" />
          <Categories>
            <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="SymmetryVelXBoundaryCondition" BaseType="VelXBoundaryCondition" Abstract="1" Version="0" Unique="true" Associations="f" />
    <AttDef Type="SymmetryVelYBoundaryCondition" BaseType="VelYBoundaryCondition" Abstract="1" Version="0" Unique="true" Associations="f" />
    <AttDef Type="SymmetryVelZBoundaryCondition" BaseType="VelZBoundaryCondition" Abstract="1" Version="0" Unique="true" Associations="f" />

    <AttDef Type="Heat Flux" BaseType="BoundaryCondition" Abstract="1" Version="0" Unique="true" Associations="f" >
      <ItemDefinitions>
        <Double Name="LoadCurve" Version="0" NumberOfRequiredValues="1" AdvanceLevel="1" Optional="true" IsEnabledByDefault="false">
          <Labels CommonLabel="Load Curve" />
          <ExpressionType>PolyLinearFunction</ExpressionType>
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
        <Double Name="Scale" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Scale" />
          <Categories>
            <Cat>Energy Equation</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>

    <!--***  Execution Definitions ***-->
    <AttDef Type="LoadBalancer" BaseType="" Abstract="0" Version="0" Unique="true">
      <ItemDefinitions>
        <String Name="Method" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
          <Labels CommonLabel="Load Balance Method"/>
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
        <Int Name="ExecutionControl" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
          <Labels CommonLabel="Execution Control Frequency Checking"/>
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
        <Group Name="Restart Output" NumberOfRequiredGroups="1">
          <ItemDefinitions>
            <Int Name="frequency" Version="0" NumberOfRequiredValues="1">
              <Labels CommonLabel="Frequency"/>
              <BriefDescription>Output dump file frequency. 0 implies do not output.</BriefDescription>
              <RangeInfo>
                <Min Inclusive="true">0</Min>
              </RangeInfo>
              <DefaultValue>0</DefaultValue>
            </Int>
            <String Name="type" Version="0" NumberOfRequiredValues="1">
              <Labels CommonLabel="Type"/>
              <BriefDescription>Output dump format</BriefDescription>
              <DiscreteInfo DefaultIndex="0">
                <Value Enum="Serial">serial</Value>
                <Value Enum="Distributed">distributed</Value>
              </DiscreteInfo>
            </String>
          </ItemDefinitions>
        </Group>
        <Group Name="Field Output" NumberOfRequiredGroups="1">
          <ItemDefinitions>
            <Int Name="frequency" Version="0" NumberOfRequiredValues="1">
              <Labels CommonLabel="Frequency"/>
              <BriefDescription>Output dump file frequency. 0 implies do not output.</BriefDescription>
              <RangeInfo>
                <Min Inclusive="true">0</Min>
              </RangeInfo>
              <DefaultValue>20</DefaultValue>
            </Int>
            <String Name="type" Version="0" NumberOfRequiredValues="1">
              <Labels CommonLabel="Type"/>
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
        <Int Name="minmaxfrequency" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
          <Labels CommonLabel="Interval to report min/max velocity values"/>
          <DefaultValue>10</DefaultValue>
          <RangeInfo>
            <Min Inclusive="yes">0</Min>
          </RangeInfo>
        </Int>
        <Int Name="tifrequency" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
          <Labels CommonLabel="Time history write frequency"/>
          <DefaultValue>1</DefaultValue>
          <RangeInfo>
            <Min Inclusive="yes">0</Min>
          </RangeInfo>
        </Int>
        <String Name="PrintLevel" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
          <Labels CommonLabel="ASCII Print Level"/>
          <BriefDescription>Controls the amount of data written to the ASCII (human-readable) output file</BriefDescription>
          <DiscreteInfo DefaultValue="0">
            <Value Enum="param">param</Value>
            <Value Enum="results">results</Value>
            <Value Enum="verbose">verbose</Value>
          </DiscreteInfo>
        </String>
        <Int Name="hcfrequency" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
          <Labels CommonLabel="Interval (only used if ASCII Print Level is verbose)"/>
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
        <Int Name="Id" Version="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Id"/>
          <RangeInfo>
            <Min Inlcusive="true">0</Min>
          </RangeInfo>
        </Int>
        <String Name="varname" Version="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Variable Name"/>
        </String>
        <Int Name="type" Version="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Type"/>
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
        <String Name="varname" Version="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Variable Name"/>
        </String>
        <Int Name="type" Version="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Type"/>
          <DiscreteInfo>
            <Value Enum="Element">elem</Value>
            <Value Enum="Node">node</Value>
          </DiscreteInfo>
        </Int>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="SideSetPlotVarOutput" BaseType="VarOutput" Abstract="0" Version="0" Unique="false">
      <ItemDefinitions>
        <Int Name="Id" Version="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Id"/>
          <RangeInfo>
            <Min Inlcusive="true">0</Min>
          </RangeInfo>
        </Int>
        <String Name="varname" Version="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Variable Name"/>
        </String>
      </ItemDefinitions>
    </AttDef>

    <!--*** Temporal statistics options ***-->
    <AttDef Type="TempStatVarOutput" BaseType="" Abstract="1" Version="0" Unique="false"/>
    <AttDef Type="NodeElemTempStatVarOutput" BaseType="TempStatVarOutput" Abstract="0" Version="0" Unique="false">
      <ItemDefinitions>
        <String Name="varname" Version="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Variable Name"/>
        </String>
        <Int Name="type" Version="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Type"/>
          <DiscreteInfo>
            <Value Enum="Element">elem</Value>
            <Value Enum="Node">node</Value>
          </DiscreteInfo>
        </Int>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="SideSetTempStatVarOutput" BaseType="TempStatVarOutput" Abstract="0" Version="0" Unique="false">
      <ItemDefinitions>
        <Int Name="Id" Version="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Id"/>
          <RangeInfo>
            <Min Inclusive="true">0</Min>
          </RangeInfo>
        </Int>
        <String Name="varname" Version="0" NumberOfRequiredValues="1">
          <Labels CommonLabel="Variable Name"/>
        </String>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="TempStatVarStatistics" BaseType="" Abstract="0" Version="0" Unique="false">
      <Group Name="Temporal Statistics" NumberOfRequiredGroups="1">
        <ItemDefinitions>
          <Double Name="StartTime" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
            <Labels CommonLabel="Start Time"/>
            <DefaultValue>0.0</DefaultValue>
          </Double>
          <Double Name="EndTime" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
            <Labels CommonLabel="End Time"/>
            <DefaultValue>1.0</DefaultValue>
          </Double>
          <Double Name="PlotWinSize" Version="0" AdvanceLevel="0" NumberOfRequiredValues="1">
            <Labels CommonLabel="Time Window Size"/>
            <DefaultValue>0.1</DefaultValue>
            <RangeInfo>
              <Min Inclusive="false">0.0</Min>
            </RangeInfo>
          </Double>
        </ItemDefinitions>
      </Group>
    </AttDef>

    <!--*** Solver Definitions ***-->
    <AttDef Type="ppesolver" BaseType="" Version="0" Unique="" Associations="">
      <ItemDefinitions>
        <Group Name="Pressure Poisson Solver" NumberOfRequiredGroups="1">
          <ItemDefinitions>
            <String Name="type" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <Labels CommonLabel="Type"/>
              <DiscreteInfo DefaultValue="0">
                <Value Enum="AMG">AMG</Value>
                <Value Enum="SSORCG">SSORCG</Value>
                <Value Enum="JPCG">JPCG</Value>
              </DiscreteInfo>
            </String>
            <Int Name="itmax" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <Labels CommonLabel="Maximum number of iterations"/>
              <BriefDescription>The maximum number of iterations. In the case of AMG, this is the maximum number of V or W cycles.</BriefDescription>
              <DefaultValue>500</DefaultValue>
              <RangeInfo>
                <Min Inclusive="false">0</Min>
                <Max Inclusive="true">500</Max>
              </RangeInfo>
            </Int>
            <Int Name="itchk" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <Labels CommonLabel="Convergence criteria checking frequency"/>
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
            <Double Name="eps" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <Labels CommonLabel="Convergence criteria"/>
              <DefaultValue>1e-05</DefaultValue>
              <RangeInfo>
                <Min Inclusive="false">0.0</Min>
                <Max Inclusive="true">1.0</Max>
              </RangeInfo>
            </Double>
            <Double Name="pivot" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <Labels CommonLabel="Preconditioner zero pivot value"/>
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
    <AttDef Type="momentumsolver" BaseType="" Version="0">
      <ItemDefinitions>
        <Group Name="Momentum Solver" NumberOfRequiredGroups="1">
          <ItemDefinitions>
            <String Name="type" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <Labels CommonLabel="Type"/>
              <DiscreteInfo DefaultValue="0">
                <Value Enum="Flexible GMRES">FGMRES</Value>
                <Value Enum="ILU-Preconditioned FGMRES">ILUFGMRES</Value>
                <Value Enum="GMRES">GMRES</Value>
                <Value Enum="ILU-Preconditioned GMRES">ILUGMRES</Value>
              </DiscreteInfo>
            </String>
            <Int Name="restart" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <Labels CommonLabel="Number of restart vectors"/>
              <DefaultValue>30</DefaultValue>
              <RangeInfo>
                <Min Inclusive="false">0</Min>
                <Max Inclusive="true">100</Max>
              </RangeInfo>
            </Int>
            <Int Name="itmax" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <Labels CommonLabel="Maximum number of iterations"/>
              <BriefDescription>The maximum number of iterations. In the case of AMG, this is the maximum number of V or W cycles.</BriefDescription>
              <DefaultValue>500</DefaultValue>
              <RangeInfo>
                <Min Inclusive="false">0</Min>
                <Max Inclusive="true">500</Max>
              </RangeInfo>
            </Int>
            <Int Name="itchk" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <Labels CommonLabel="Convergence criteria checking frequency"/>
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
            <Double Name="eps" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <Labels CommonLabel="Convergence criteria"/>
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
    <!--*** The transport solver is the same as the momentum solver ***-->
    <AttDef Type="transportsolver" BaseType="momentumsolver" Version="0"/> <!-- acbauer - shjows up as Momentum Solver in gui -->

    <!--*** Solution method ***-->
    <AttDef Type="solution_method" BaseType="" Version="0">
      <ItemDefinitions>
        <Group Name="Solution Method" NumberOfRequiredGroups="1">
          <ItemDefinitions>
            <String Name="strategy" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <Labels CommonLabel="Strategy"/>
              <DiscreteInfo DefaultValue="0">
                <Value Enum="Projection">projection</Value>
                <Value Enum="Picard">picard</Value>
              </DiscreteInfo>
            </String>
            <String Name="pressure_update" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <Labels CommonLabel="Pressure Update"/>
              <DiscreteInfo DefaultValue="1">
                <Value Enum="Chorin">chorin</Value>
                <Value Enum="Gradient">gradient</Value>
                <Value Enum="Curvature">curvature</Value>
              </DiscreteInfo>
            </String>
            <Void Name="curlfree_fix" Label="Curl Free Fix (Picard only)" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1" Optional="true" IsEnabledByDefault="false">
              <BriefDescription>Enable/disable the diagnostic information from the solver.</BriefDescription>
            </Void>
            <Int Name="itmax" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <Labels CommonLabel="Maximum number of iterations"/>
              <BriefDescription>The maximum number of non-linear iterations to be taken during each time step.</BriefDescription>
              <DefaultValue>5</DefaultValue>
              <RangeInfo>
                <Min Inclusive="false">0</Min>
              </RangeInfo>
            </Int>
            <Double Name="eps" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <Labels CommonLabel="Convergence criteria"/>
              <DefaultValue>1e-04</DefaultValue>
              <RangeInfo>
                <Min Inclusive="false">0.0</Min>
                <Max Inclusive="true">1.0</Max>
              </RangeInfo>
            </Double>
            <Double Name="eps_dist" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <Labels CommonLabel="Convergence criteria for normal-distance function"/>
              <DefaultValue>1e-05</DefaultValue>
              <RangeInfo>
                <Min Inclusive="false">0.0</Min>
                <Max Inclusive="true">1.0</Max>
              </RangeInfo>
            </Double>
            <Double Name="eps_p0" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <Labels CommonLabel="Convergence criteria for initial div-free projection and initial pressure computation"/>
              <DefaultValue>1e-05</DefaultValue>
              <RangeInfo>
                <Min Inclusive="false">0.0</Min>
                <Max Inclusive="true">1.0</Max>
              </RangeInfo>
            </Double>
            <Double Name="velrelax" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <Labels CommonLabel="Velocity update under-relaxation parameter"/>
              <DefaultValue>1.0</DefaultValue>
              <RangeInfo>
                <Min Inclusive="true">0.0</Min>
                <Max Inclusive="true">1.0</Max>
              </RangeInfo>
            </Double>
            <Double Name="enerelax" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <Labels CommonLabel="Energy update under-relaxation parameter"/>
              <DefaultValue>1.0</DefaultValue>
              <RangeInfo>
                <Min Inclusive="true">0.0</Min>
                <Max Inclusive="true">1.0</Max>
              </RangeInfo>
            </Double>
            <Double Name="nutrelax" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <Labels CommonLabel="Spalart-Allmaras under-relaxation parameter"/>
              <DefaultValue>1.0</DefaultValue>
              <RangeInfo>
                <Min Inclusive="true">0.0</Min>
                <Max Inclusive="true">1.0</Max>
              </RangeInfo>
            </Double>
            <Double Name="tkerelax" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <Labels CommonLabel="Turbulent kinetic energy under-relaxation parameter"/>
              <DefaultValue>1.0</DefaultValue>
              <RangeInfo>
                <Min Inclusive="true">0.0</Min>
                <Max Inclusive="true">1.0</Max>
              </RangeInfo>
            </Double>
            <Double Name="epsrelax" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <Labels CommonLabel="Turbulent dissipation rate under-relaxation parameter"/>
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

    <!--*** Time integration  method ***-->
    <AttDef Type="time_integration" BaseType="" Version="0">
      <ItemDefinitions>
        <Group Name="Time Integration" NumberOfRequiredGroups="1">
          <ItemDefinitions>
            <String Name="type" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <Labels CommonLabel="Method"/>
              <DiscreteInfo DefaultValue="0">
                <Value Enum="Fixed CFL">fixed_cfl</Value>
                <Value Enum="Fixed time step">fixed_dt</Value>
              </DiscreteInfo>
            </String>
            <Double Name="thetaa" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <Labels CommonLabel="Time weight for advective terms"/>
              <BriefDescription>Value of 0 indicates explicit advection and value of 1 indicates implicit</BriefDescription>
              <DefaultValue>0.5</DefaultValue>
              <RangeInfo>
                <Min Inclusive="true">0.0</Min>
                <Max Inclusive="true">1.0</Max>
              </RangeInfo>
            </Double>
            <Double Name="thetaK" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <Labels CommonLabel="Time weight for advective terms"/>
              <BriefDescription>Value of 0 indicates explicit advection and value of 1 indicates implicit</BriefDescription>
              <DefaultValue>0.5</DefaultValue>
              <RangeInfo>
                <Min Inclusive="true">0.0</Min>
                <Max Inclusive="true">1.0</Max>
              </RangeInfo>
            </Double>
            <Double Name="thetafF" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
              <Labels CommonLabel="Time weight for source terms"/>
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
    <AttDef Type="RNG k-e" BaseType="Turbulence model" Abstract="0" Version="0">
      <ItemDefinitions>
        <Void Name="timescale_limiter" Label="Time scale limiter" Version="0" AdvanceLevel="1" Optional="true" IsEnabledByDefault="false" NumberOfRequiredValues="0">
          <Categories>
             <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Void>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="Smagorinsky" BaseType="Turbulence model" Abstract="0" Version="0">
      <ItemDefinitions>
        <Double Name="c_s" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
          <Labels CommonLabel="Constant"/>
          <BriefDescription>Smagorinsky model constant.</BriefDescription>
          <DefaultValue>0.18</DefaultValue>
          <Categories>
             <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="prandtl" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
          <Labels CommonLabel="Turbulent Prandtl number"/>
          <DefaultValue>0.889</DefaultValue>
          <Categories>
             <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="schmidt" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
          <Labels CommonLabel="Turbulent Schmidt number"/>
          <DefaultValue>1.0</DefaultValue>
          <Categories>
             <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
      </ItemDefinitions>
    </AttDef>
    <AttDef Type="WALE" BaseType="Turbulence model" Abstract="0" Version="0">
      <ItemDefinitions>
        <Double Name="c_w" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
          <Labels CommonLabel="Constant"/>
          <BriefDescription>WALE model constant.</BriefDescription>
          <DefaultValue>0.18</DefaultValue>
          <Categories>
             <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="prandtl" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
          <Labels CommonLabel="Turbulent Prandtl number"/>
          <DefaultValue>0.889</DefaultValue>
          <Categories>
             <Cat>Incompressible Navier-Stokes</Cat>
          </Categories>
        </Double>
        <Double Name="schmidt" Version="0" AdvanceLevel="1" NumberOfRequiredValues="1">
          <Labels CommonLabel="Turbulent Schmidt number"/>
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
    <AttDef Type="PolyLinearFunction" BaseType="SimInterpolation" Version="0" Unique="true" Associations="">
      <ItemDefinitions>
        <Group Name="ValuePairs" NumberOfRequiredGroups="1">
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
        <Att Type="solution_method">Solution Method</Att>
        <Att Type="time_integration">Time Integration</Att>
        <!-- acbauer - put turbulence stuff here when ready -->
        <Att Type="ProblemDefinition">Time Dependence</Att>
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

    <AttributeView Title="Temporal Statistics">
      <!-- TempStatVarStatistics isn't appearing in the GUI -->
      <InstancedAttributes>
        <Att Type="TempStatVarStatistics">Parameters</Att> <!-- acbauer - this isn't showing in gui - fix this -->
      </InstancedAttributes>
      <AttributeTypes>
        <Type>TempStatVarOutput</Type>
      </AttributeTypes>
    </AttributeView>

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
