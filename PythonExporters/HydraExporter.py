import smtk

# Common data structures & functions
ConfigData = type('ConfigData', (object,), dict())
def standard_section(attribute_type, title=None, group_name=None, comment=None):
    config = ConfigData()
    config.type = 'standard'
    config.attribute_type = attribute_type
    config.title = title
    config.group_name = group_name
    config.comment = comment
    return config
ss = standard_section  # shorthand
def boundary_condition_section(attribute_type, title=None):
    config = ConfigData()
    config.type = 'boundary_condition'
    config.attribute_type = attribute_type
    config.title = title
    return config
bc = boundary_condition_section
def custom_section(section_name):
    config = ConfigData()
    config.type = 'custom'
    config.section_name = section_name
    return config
def item_format(item_name, keyword=None, item_format_list=None):
    config = ConfigData()
    config.name = item_name
    config.keyword = item_name if keyword is None else keyword
    config.item_format_list = item_format_list  # conditional children
    return config
fmt = item_format  # shorthand
def group_format(group_name, item_format_list):
    config = ConfigData()
    config.group_name = group_name
    config.name = group_name  # TODO revist adding Config.type?
    config.item_format_list = item_format_list
    return config
groupfmt = group_format  # shorthand
# Note to self: At some point, we may need to add an optional list to
# standard_section for the strings to write for Item instances
# represented as discrete values. So far haven't needed it.


# ==================================================
#
# List of output section config data
# Standard config is ss(attribute-type, [hydra-title, group-item-name, comment-line])
#
# ==================================================
section_table = [
    ss('simulationtime'),
    ss('solution_method', 'solution_method', 'SolutionMethod'),
    ss('time_integration', 'time_integration', 'TimeIntegration'),
    ss('LoadBalancer', 'load_balance'),
    custom_section('output'),
    ss('energy'),
    custom_section('hydrostat'),
    custom_section('turbulence'),
    ss('Material', 'material', comment='Material model setup & assignment to sets'),
    # TODO materialset, probably a custom_section (or part of a custom Material section)
    custom_section('plotvar'),
    custom_section('histvar'),
    custom_section('plotstatvar'),
    #ss('InitialConditions', 'initial', 'InitialConditions', comment='Simple IC\'s'),
    custom_section('InitialConditions'),
    custom_section('BodyForce'),
    bc('distancebc', 'distance'),
    bc('Pressure', 'pressure'),
    #custom_section('distance'),  # Wall and Penetration att types
    bc('TurbulentViscosity', 'turbnu'),
    custom_section('velocity'),  # 6 different att types
    #vector_bc('velocity', [
    #        ('VelXBoundaryCondition', 'velx'),
    #        ('VelYBoundaryCondition', 'vely'),
    #        ('VelZBoundaryCondition', 'velz'),
    #    ],
    #)
    # TODO remaining boundary condition types
    ss('ppesolver', 'ppesolver', 'PressurePoissonSolver'),
    ss('momentumsolver', 'momentumsolver', 'MomentumSolver'),
    ss('transportsolver', 'transportsolver', 'TransportSolver'),
]

# ==================================================
#
# Dictionary of format config data for items contained in attributes
# Format is fmt(item-name, hydra-keyword-if-different)
# The group_format (groupfmt) identifier should *only* be used for custom sections
#
# ==================================================
format_table = {
    'simulationtime': [
        fmt('nsteps'),
        fmt('deltat'),
        fmt('term')
    ],
    'energy': [
        fmt('energy')
    ],
    'solution_method': [
        fmt('strategy', 'strategy', [
                fmt('error_norm'),
                fmt('nvec')]
            ),
        fmt('itmax'),
        fmt('eps'),
        fmt('eps_dist'),
        fmt('eps_p0'),
        fmt('subcycle'),
        fmt('timestep_control'),
        fmt('convergence'),
        fmt('diagnostics')
    ],
    'time_integration': [
        fmt('type'),
        fmt('CFLinit'),
        fmt('CFLmax'),
        fmt('dtmax'),
        fmt('dtscale'),
        fmt('thetaa'),
        fmt('thetaK', 'thetak'),
        fmt('thetaf'),
        fmt('trimlast')
    ],
    'LoadBalancer': [
        fmt('Method', 'method'),
        fmt('Load Balance Diagnostics', 'diagnostics')
    ],
    'Output': [
        fmt('type', 'filetype'),
        groupfmt('FieldOutput', [
            fmt('type', 'pltype'),
            fmt('frequency', 'plti')
        ]),
        groupfmt('RestartOutput', [
            fmt('frequency', 'dump')
        ])
    ],
    'StatusInformation': [
        fmt('minmaxfrequency', 'ttyi'),
        fmt('tifrequency', 'thti'),
        fmt('PrintLevel', 'prtlev', [
            fmt('hcfrequency', 'prti')
        ]),
    ],
    'BasicTurbulenceModel': [
        fmt('Method', 'turbulence', [
            fmt('timescale_limiter'),
            fmt('c_s'),
            fmt('c_w'),
            fmt('prandtl'),
            fmt('schmidt'),
        ]),
    ],
    'Material': [
        fmt('Density', 'rho'),
        fmt('mu')
    ],
    'InitialConditions': [
        fmt('Velocity', ['velx', 'vely', 'velz']),
        fmt('tv', 'turbnu'),
        fmt('tke'),
        fmt('itdr', 'eps')
    ],
    'ppesolver': [
        fmt('ppetype', 'type'),
        fmt('itmax'),
        fmt('itchk'),
        fmt('diagnostics'),
        fmt('convergence'),
        fmt('eps'),
        fmt('pivot', 'zeropivot'),
        fmt('ppetype', 'type', [
            fmt('preconditioner', 'amgpc', [
                fmt('hypre_coarsen_type'),
                fmt('hypre_smoother'),
                fmt('hypre_smoother_dn'),
                fmt('hypre_smoother_up'),
                fmt('hypre_smoother_co'),
                fmt('interp_type'),
                fmt('trunc_factor'),
                fmt('pmax_elements'),
                fmt('agg_num_levels'),
                fmt('strong_threshold'),
                fmt('max_rowsum'),
                fmt('smoother'),
                fmt('cycle'),
                fmt('solver'),
                fmt('pre_smooth'),
                fmt('post_smooth'),
                fmt('coarse_size'),
                fmt('levels'),
            ])
        ])
    ],
    'momentumsolver': [
        fmt('type'),
        fmt('restart'),
        fmt('itmax'),
        fmt('itchk'),
        fmt('diagnostics'),
        fmt('convergence'),
        fmt('eps'),
    ],
    'transportsolver': [
        fmt('type'),
        fmt('restart'),
        fmt('itmax'),
        fmt('itchk'),
        fmt('diagnostics'),
        fmt('convergence'),
        fmt('eps'),
    ],
}

# Instantiate global dicationary for load curve functions
lcid_dictionary = dict()


# Entry point (main export function)
def ExportCMB(spec):
    '''
    Entry function, called by CMB to write export file
    '''
    manager = spec.getSimulationAttributes()
    export_manager = spec.getExportAttributes()
    #analysis_name = spec.getAnalysisNames()[0]  # deprecated
    #output_file_name = spec.getOutputPath()     # deprecated

    ok = True

    if manager is None:
        print 'No attribute manager found - no output generated'
        return False

    if export_manager is None:
        print 'No export attributes found - no output generated'
        return False

    att_list = export_manager.findAttributes('ExportSpec')
    if len(att_list) < 1:
        print 'ERROR - missing ExportSpec attribute'
        return False
    elif len(att_list) > 1:
        print 'ERROR - multiple ExportSpec attributes'
        return False
    spec_att = att_list[0]

    item = spec_att.find('AnalysisTypes')
    if item is None:
        print 'ERROR - ExportSpec attribute missing AnalysisTypes item'
        return False
    types_item = smtk.attribute.to_concrete(item)

    analysis_type = 'Default'
    if types_item.numberOfValues() < 1:
        print 'Warning: No analysis type specified'
    else:
        analysis_type = types_item.value(0)
    if types_item.numberOfValues() > 1:
        print 'More than 1 Analysis Type specified: using 1st one'
    print 'Exporting analysis type', analysis_type

    output_file_name = 'output.txt'  # default
    item = spec_att.find('OutputFile')
    if item is not None:
        output_item = smtk.attribute.to_concrete(item)
        if output_item.isSet(0):
            value = output_item.value(0)
            if value != '':
                output_file_name = value
    print 'Writing output file', output_file_name

    analysis_dict = {
        'Incompressible Navier-Stokes Analysis': 'cc_navierstokes',
        'NS and Energy Equation Analysis': 'cc_navierstokes'
    }
    if analysis_type not in analysis_dict:
        print 'Unsupported analysis type \"%s\"" - no output generated' % \
            analysis_type
        return False

    categories = list(manager.analysisCategories(analysis_type))
    print 'categories', categories
    if not categories:
        print 'WARMING: No categories found for analysis \"%s\"' % \
          analysis_type
        #return False

    # Instantiate output file and write contents
    with open(output_file_name, 'w') as out:
        out.write('title\n')
        out.write('Hydra-TH control file generated by Kitware CMB\n')
        out.write('\n')

        title = analysis_dict.get(analysis_type, 'unknown_analysis')
        out.write(title)
        out.write('\n')

        # Process elements in section_table
        for section_config in section_table:
            ok = write_section(manager, section_config, categories, out)

        # Write load curves last, since ids are assigned when writing atts
        write_load_curves(manager, out)

        out.write('\n')
        out.write('end\n')
        out.write('\n')
        out.write('exit\n')

    print 'Export ok status: %s' % ok
    return ok

def get_id_from_name(name):
    '''
    A hack by acbauer to get the sideset or cell block id from
    the model entity's name. it assumes that the last token
    in the string is the proper id to be used. This will be
    replaced with GridInfo when we have time to do it properly.
    '''
    # Domain sets are named DomainSetX
    domainset_prefix = 'DomainSet'
    if name.startswith(domainset_prefix):
        l = len(domainset_prefix)
        return name[l:]

    tokens = name.split()
    if tokens: # checks if tokens is empty
        return tokens[-1]

    return "BAD_VALUE"

def write_output_section(manager, categories, out):
    '''
    Writes output section, which is "custom" because spans multiple attributes
    '''
    out.write('\n')
    out.write('  # Output options\n')

    # This is awkward - must put keyword as last item in the list, instead
    # of Item name, because format_table[] is set up that way
    # TODO Redo format table to put Item name first?
    write_item(manager, categories, out, 'Output', 'FieldOutput', 'type')  # pltype
    #write_item(manager, categories, out, 'Output', 'RestartOutput', 'type')  # filetype
    write_item(manager, categories, out, 'Output', 'type')  # filetype
    write_item(manager, categories, out, 'Output', 'FieldOutput', 'frequency')  # plti
    write_item(manager, categories, out, 'StatusInformation', 'minmaxfrequency')  # ttyi
    write_item(manager, categories, out, 'StatusInformation', 'tifrequency')  # thti

    #write_item(manager, categories, out, 'StatusInformation', 'PrintLevel')  # prtlev
    # Because PrintLevel has conditional children, use write_item_tree() method
    # Suggests some better refactoring of write_item() and write_item_tree()
    item = find_item(manager, 'StatusInformation', 'PrintLevel')
    if item.isMemberOf(categories):
        item_config = find_item_config('StatusInformation', 'PrintLevel')
        format_string = '  %s %s\n'
        write_item_tree(item, item_config, format_string, out)

    write_item(manager, categories, out, 'Output', 'RestartOutput', 'frequency')  # dump
    return True


def write_turbulence_section(manager, categories, out):
    '''
    Writes turbulence section for AdvancedTurbulenceModel attribute
    Hydra-TH format is slightly nonstandard
    '''
    att_type = 'BasicTurbulenceModel'
    turb_att_list = manager.findAttributes(att_type)
    if len(turb_att_list) < 1:
        return True

    item_format_list = format_table.get(att_type)
    if item_format_list is None:
        print 'WARNING: No format info for', att_type
        return False

    attribute = turb_att_list[0] # there should only be a single instance of this attribute

    if not attribute.isMemberOf(categories):
        return True

    item = attribute.find("Method")
    if item is None:
        return False

    if not item.isEnabled():
        return True

    item = smtk.attribute.to_concrete(item)

    out.write('\n')
    if item.value(0) in ["WALE", "rng_ke", "smagorinsky"]:
        format_string = '  %s %s\n'
        for turb_att in turb_att_list:
            out.write('\n')
            for item_config in item_format_list:
                item = turb_att.find(item_config.name)
                if item is None:
                    continue

                write_item_tree(item, item_config, format_string, out, indent='  ')
                out.write('  end\n')
    else:
        out.write('  tmodel %s\n' % item.value(0))

    return True


def write_plotvar_section(manager, categories, out, name):
    '''
    Writes plotvar section for [Node/Elem/SideSet]PlotVarOutput attributes
    '''
    config = {
        'plotvar': ('NodePlotVarOutput', 'ElemPlotVarOutput', 'SideSetPlotVarOutput'),
        'plotstatvar': ('NodeTempStatVarOutput', 'ElemTempStatVarOutput', 'SideSetTempStatVarOutput'),
        }

    node_att_list = manager.findAttributes(config[name][0])
    elem_att_list = manager.findAttributes(config[name][1])
    ss_att_list = manager.findAttributes(config[name][2])
    if len(node_att_list) + len(elem_att_list) + len(ss_att_list) < 1:
        return True

    if name == 'plotstatvar':
        out.write('\n')
        out.write('  statistics\n')
        plotstatvaratt = manager.findAttributes('TempStatVarStatistics')[0]
        itemlabels = ['starttime', 'endtime', 'plotwinsize']
        groupitem = plotstatvaratt.find('TemporalStatistics')
        var_groupitem = smtk.attribute.to_concrete(groupitem)
        for i in range(len(itemlabels)):
            item = var_groupitem.item(i)
            var_item = smtk.attribute.to_concrete(item)
            out.write('    %s %s\n' % (itemlabels[i],var_item.value(0)) )
        out.write('  end\n')

    out.write('\n')
    out.write('  %s\n' % name)

    types = [ 'node', 'elem']
    lists = [ node_att_list, elem_att_list]
    # Create list of (type, varname) tuples
    ne_tlist = list()
    for i in range(len(lists)):
        plot_type = types[i]
        current_list = lists[i]
        for att in current_list:
            item = att.find('varname')
            var_item = smtk.attribute.to_concrete(item)
            t = (plot_type, var_item.value(0))
            ne_tlist.append(t)

    ne_tlist.sort()
    for t in ne_tlist:
        out.write('    %s %s\n' % t)

    # Create list of (type, sideset id, varname) tuples
    ss_tlist = list()
    for att in ss_att_list:
        item = att.find('varname')
        var_item = smtk.attribute.to_concrete(item)
        entities = att.associatedEntitiesSet()
        for entity in entities:
            #out.write('    block %s\n' % get_id_from_name(entity.name()))
            t = ('side ', get_id_from_name(entity.name()), var_item.value(0))
            ss_tlist.append(t)

    ss_tlist.sort()

    for t in ss_tlist:
        out.write('    %s %s %s\n' % t)

    out.write('  end\n')
    return True

def write_histvar_section(manager, categories, out):
    '''
    Writes histvar section for [Node/Elem/SideSet]HistVarOutput attributes
    '''
    node_att_list = manager.findAttributes('NodeHistVarOutput')
    elem_att_list = manager.findAttributes('ElemHistVarOutput')
    ss_att_list = manager.findAttributes('SideSetHistVarOutput')
    if len(node_att_list) + len(elem_att_list) + len(ss_att_list) < 1:
        return True

    out.write('\n')
    out.write('  histvar\n')

    types = [ 'node', 'elem']
    lists = [ node_att_list, elem_att_list]

    # Create list of (type, varname) tuples
    ne_tlist = list()
    for i in range(len(lists)):
        plot_type = types[i]
        for att in lists[i]:
            item = att.find('varname')
            var_item = smtk.attribute.to_concrete(item)
            item = att.find('Id')
            id_item = smtk.attribute.to_concrete(item)
            t = (plot_type, id_item.value(0), var_item.value(0))
            ne_tlist.append(t)

    ne_tlist.sort()
    for t in ne_tlist:
        out.write('    %s %s %s\n' % t)

    # Create list of (type, sideset id, varname) tuples
    ss_tlist = list()
    for att in ss_att_list:
        item = att.find('varname')
        var_item = smtk.attribute.to_concrete(item)
        entities = att.associatedEntitiesSet()
        for entity in entities:
            #out.write('    block %s\n' % get_id_from_name(entity.name()))
            t = ('side ', get_id_from_name(entity.name()), var_item.value(0))
            ss_tlist.append(t)

    ss_tlist.sort()

    for t in ss_tlist:
        out.write('    %s %s %s\n' % t)

    out.write('  end\n')
    return True

def  write_hydrostat_section(manager, categories, out):
    '''
    Writes hydrostat section
    '''
    att_list = manager.findAttributes('hydrostat')
    if not att_list:
        print 'WARNING - expected hydrostat attribute'
        return False

    att = att_list[0]
    if not att.isMemberOf(categories):
        return True

    item = att.find('Hydrostat')
    if item is None:
        return False

    if not item.isEnabled():
        return True

    out.write('\n')
    out.write('  # Hydrostatic pressure\n')
    out.write('  hydrostat\n')

    group_item = smtk.attribute.to_concrete(item)
    item = group_item.find('NodesetId')
    nsid_item = smtk.attribute.to_concrete(item)
    nsid = nsid_item.value(0)

    lcid = -1
    item = group_item.find('Value')
    if item.isEnabled():
        lcid_item = smtk.attribute.to_concrete(item)
        lcid = get_loadcurve_id(lcid_item)

    item = group_item.find('Scale')
    scale_item = smtk.attribute.to_concrete(item)
    scale = scale_item.value(0)

    output = '    nodesetid %d %d %s\n' % (nsid, lcid, scale)
    out.write(output)
    out.write('  end\n')


def write_bc_section(manager, section_config, categories, out):
    '''
    Writes boundary condition section
    Most have the same general format
    '''
    att_list = manager.findAttributes(section_config.attribute_type)
    if len(att_list) < 1:
        return True

    out.write('\n')
    out.write('  %s\n' % section_config.title)

    for att in att_list:
        if not att.isMemberOf(categories):
            continue

        ent_set = att.associatedEntitiesSet()
        # TODO sort by sideset number (is this a UserData thing?)
        for ent in ent_set:
            sideset = get_id_from_name(ent.name())

            item = att.find('LoadCurve')
            lcid = get_loadcurve_id(item)
            if lcid is None:
                lcid = -1

            # TODO use format table? Only for non-standard form
            item = att.find('Scale')
            scale_item = smtk.attribute.to_concrete(item)
            scale = get_item_value(scale_item)
            out.write('    sideset %s %d %s\n' % (sideset, lcid, scale))

    out.write('  end\n')
    return True


def write_distance_section(manager, categories, out):
    '''
    Writes distance section using both Wall and Penetration attributes
    '''
    wlist = manager.findAttributes('Wall')
    plist = manager.findAttributes('Penetration')
    if (len(wlist) + len(plist)) < 1:
        return True

    out.write('\n')
    out.write('  distance\n')

    # Write walls first (no load curve, zero scale)
    for att in wlist:
        if not att.isMemberOf(categories):
            continue

        ent_set = att.associatedEntitiesSet()
        # TODO sort by sideset number
        for ent in ent_set:
            sideset = get_id_from_name(ent.name())
            out.write('    sideset %s -1 0.0\n' % sideset)

    # Then write penetration atts
    for att in plist:
        ent_set = att.associatedEntitiesSet()
        # TODO sort by sideset number
        for ent in ent_set:
            sideset = get_id_from_name(ent.name())

            item = att.find('LoadCurve')
            lcid = get_loadcurve_id(item)
            if lcid is None:
                lcid = -1

            item = att.find('Scale')
            scale_item = smtk.attribute.to_concrete(item)
            scale = get_item_value(scale_item)
            out.write('    sideset %s %d %s\n' % (sideset, lcid, scale))

    out.write('  end\n')
    return True


def write_velocity_section(manager, categories, out):
    '''
    Writes velocity boundary conditions as a custom section
    Calls generic write_vector_bc_section() function
    TODO - migrate to section_table
    '''
    config = ConfigData()
    config.section_title = 'velocity'
    config.attribute_types_labels = (
        ('VelXBoundaryCondition', 'velx'),
        ('VelYBoundaryCondition', 'vely'),
        ('VelZBoundaryCondition', 'velz')
    )
    return write_vector_bc_section(manager, config, categories, out)


def write_vector_bc_section(manager, config, categories, out):
    '''
    Internal method for writing multiple/labeled BCs in same section
    '''
    # Traverse all att types to generate dictionary of association info
    bc_dict = dict()  # key = sset number, value = list(att1, att2, ...)
    for att_type, label in config.attribute_types_labels:
        att_list = manager.findAttributes(att_type)
        for att in att_list:
            if not att.isMemberOf(categories):
                continue

            ent_set = att.associatedEntitiesSet()
            for ent in ent_set:
                sideset = get_id_from_name(ent.name())
                ent_att_list = bc_dict.get(sideset)
                if ent_att_list is None:
                    ent_att_list = list()
                    bc_dict[sideset] = ent_att_list
                ent_att_list.append(att)

    # Check that at least one attribute was found
    if len(bc_dict) < 1:
        return True

    # Create dictionary of <attribute type, label>
    label_dict = dict()
    for att_type, label in config.attribute_types_labels:
        label_dict[att_type] = label

    out.write('\n')
    out.write('  %s\n' % config.section_title)

    # Traverse bc_dict to write output, sorted by sideset number
    sideset_list = sorted(bc_dict.keys())
    for sideset in sideset_list:
        ent_att_list = bc_dict.get(sideset)
        for att in ent_att_list:
            label = label_dict.get(att.type())

            item = att.find('LoadCurve')
            lcid = get_loadcurve_id(item)
            if lcid is None:
                lcid = -1

            item = att.find('Scale')
            double_item = smtk.attribute.to_concrete(item)
            scale = get_item_value(double_item)
            out.write('    %s sideset %s %d %s\n' % \
                (label, sideset, lcid, scale))

    out.write('  end\n')
    return True


def write_initial_conditions_section(manager, categories, out):
    '''
    Writes initial conditions section
    Has custom logic for different turbulence models
    '''
    att_type = 'InitialConditions'
    att_list = manager.findAttributes(att_type)
    att = att_list[0]
    if not att.isMemberOf(categories):
        True

    item = att.find('InitialConditions')
    group_item = smtk.attribute.to_concrete(item)

    # Get the turbulence model
    turb_att_list = manager.findAttributes('BasicTurbulenceModel')
    turb_att = turb_att_list[0]
    item = turb_att.find('Method')
    turb_method_item = smtk.attribute.to_concrete(item)
    if turb_method_item.isEnabled():
        turb_method = turb_method_item.value(0)
    else:
        turb_method = None

    out.write('\n')
    out.write('  # Initial Conditions\n')
    out.write('  initial\n')
    format_string = '    %s %s\n'  # for individual items

    # Traverse items in format table
    format_list = format_table.get(att.type())
    if format_list is None:
        print 'WARNING: empty format list for %s' % att.type()
        return False
    for item_config in format_list:
        item = group_item.find(item_config.name)
        if item is None:
            #print 'WARNING: No %s item found' % item_config.name
            continue

        if not item.isMemberOf(categories):
            continue

        # Filter turbulence ICs based on turb_method
        if item_config.name == 'tke' and turb_method not in ['rng_ke', 'sst_kw']:
            continue
        elif item_config.name == 'itdr' and turb_method != 'rng_ke':
            continue
        elif item_config.name == 'itds' and turb_method != 'sst_kw':
            continue
        elif item_config.name == 'tv' and turb_method not in ['spalart_allmaras', 'spalart_allmaras_des']:
            continue

        concrete_item = smtk.attribute.to_concrete(item)
        if isinstance(item_config.keyword, (list, tuple)):
            # TODO Check that Item has enough values
            for i in range(len(item_config.keyword)):
                value = get_item_value(concrete_item, i)
                out.write(format_string % (item_config.keyword[i], value))
        else:
            value = get_item_value(concrete_item)
            out.write(format_string % (item_config.keyword, value))

    out.write('  end\n')
    return True

def write_body_force_section(manager, categories, out):
    '''
    Write the body_force, boussinesqforce and porous_drag section
    of the cntl file.
    '''
    att_types = ['GravityForce', 'BoussinesqForce', 'porous_drag', 'HeatSource']
    model = None  # if needed for un-associated attributes
    domain_sets = None  # ditto (only created if needed)
    for att_type in att_types:
        att_list = manager.findAttributes(att_type)
        if not att_list:
            continue

        # Traverse once to find any unassociated attributes
        # These become "default" value for domain sets
        # Also populate model_domains set if needed
        unassociated_att = None
        for att in att_list:
            if not att.isMemberOf(categories):
                continue

            if 0 == att.numberOfAssociatedEntities():
                if unassociated_att is None:
                    unassociated_att = att
                else:
                    msg = 'WARNING: more than one unassociated %s attribute.' % \
                        att_type
                    msg += ' Using \"%s\" and ignoring \"%s\"' % \
                        (unassociated_att.name(), att.name())
                    print msg
            elif model is None:
                # Retrieve set of all model domain sets
                entities = att.associatedEntitiesSet()
                model = entities.pop().model()
                #print 'Retrieved model'
                domain_sets = get_domain_sets(model)

        # Traverse again to actually write the output.
        # Keep track of which domains get output.
        unused_domain_sets = set()
        if domain_sets is not None:
            unused_domain_sets = set(domain_sets)
        for att in att_list:
            entities = att.associatedEntitiesSet()
            for entity in entities:
                unused_domain_sets.discard(entity)
                write_body_force(att, entity, out)

        # Write default values for unassociated domain sets
        if unassociated_att is not None:
            if domain_sets is None:
                msg = 'WARNING: Cannot write body force %s for unassociated attribute %s.' % \
                    (unassociated_att.type(), unassociated_att.name())
                msg = ' This version of software only supports body force' + \
                    ' attributes that are associated to one or more model entities.'
                print msg
            else:
                for entity in unused_domain_sets:
                    write_body_force(att, entity, out)


def write_body_force(att, entity, out):
    '''Writes body force for one attribute-entity pair.

    '''
    #print 'Writing', att.type(), 'for', entity.name()
    att_keywords = {'GravityForce' : ['fx', 'fy', 'fz'],
                     'BoussinesqForce' : ['gx', 'gy', 'gz'],
                     'porous_drag' : ['amp'], 'HeatSource' : ['Q'] }
    att_type = att.type()
    out.write('\n')
    out.write('  %s\n' % att_type)
    out.write('    set %s\n' % get_id_from_name(entity.name()))
    # Load curve item has same name as attribute (our policy)
    item = att.find(att_type)
    loadcurve_id = get_loadcurve_id(item)
    if loadcurve_id is not None:
        out.write('    lcid %d\n' % loadcurve_id)

    # Value is 'Scale' item
    keywords = att_keywords[att_type]
    item = att.find('Scale')
    double_item = smtk.attribute.to_concrete(item)
    for i, keyword in enumerate(keywords):
        value = double_item.value(i)
        out.write('    %s %f\n' % (keyword, value))
    out.write('  end\n')



def write_item(manager, categories, out, attribute_type, *item_names):
    '''
    Used for custom sections, retrieves and writes one item
    The code traverses the manager and format_table in lockstep
    '''

    item = find_item(manager, attribute_type, *item_names)
    if item is None:
        print 'Item %s:%s not found' % (attribute_type, '/'.join(item_names))
        return False

    # Check categories
    if not item.isMemberOf(categories):
        return

    item_config = find_item_config(attribute_type, *item_names)
    if item_config is None:
        print 'Format for Item %s:%s not found' % \
            (attribute_type, '/'.join(item_names))
        return False

    out.write('  %s %s\n' % (item_config.keyword, item.value(0)))


def write_item_tree(item, item_config, format_string, out, indent=None):
    '''
    Writes item plus any conditional children (recursively)
    '''
    item = smtk.attribute.to_concrete(item)

    # Keyword may be single string *or* list of strings
    if isinstance(item_config.keyword, (list, tuple)):
        # TODO Check that Item has enough values
        for i in range(len(item_config.keyword)):
            value = get_item_value(concrete_item, i)
            out.write(format_string % (item_config.keyword[i], value))
    else:
        value = get_item_value(item)
        out.write(format_string % (item_config.keyword, value))

    # Process any conditional children
    if item_config.item_format_list is not None:
        if indent is not None:
            format_string = '%s%s' % (indent, format_string)

        # Construct dictionary of subitems by name
        subitem_dict = dict()
        num_subitems = item.numberOfActiveChildrenItems()
        for i in range(num_subitems):
            subitem = item.activeChildItem(i)
            subitem_dict[subitem.name()] = subitem

        # Traverse subitem config instances
        for subitem_config in item_config.item_format_list:
            subitem = subitem_dict.get(subitem_config.name)
            if subitem is None:
                continue
            write_item_tree(subitem, subitem_config, format_string, out)



materialCounter = 1 # acbauer -- global counter for materials
materialSetCounter = 1 # acbauer -- global counter for material sets


def write_section(manager, section_config, categories, out):
    '''
    Writes one section of output file
    Returns boolean reflecting success/fail
    '''
    if section_config.type == 'custom':
        custom_dict = {
            #'distance': write_distance_section,
            'output':   write_output_section,
            'turbulence': write_turbulence_section,
            'histvar':  write_histvar_section,
            'hydrostat': write_hydrostat_section,
            'velocity': write_velocity_section,
            'InitialConditions': write_initial_conditions_section,
            'BodyForce': write_body_force_section
        }
        f = custom_dict.get(section_config.section_name)
        if f:
            return f(manager, categories, out)
        plotvar_dict = {
            'plotvar':  write_plotvar_section,
            'plotstatvar':  write_plotvar_section
        }
        f = plotvar_dict.get(section_config.section_name)
        if f is None:
            print 'WARNING - Cannot find custom function for %s' % \
                section_config.section_name
            return False
        return f(manager, categories, out, section_config.section_name)

    elif section_config.type == 'boundary_condition':
        return write_bc_section(manager, section_config, categories, out)


    att_list = manager.findAttributes(section_config.attribute_type)
    if not att_list:
        print 'WARNING - NO %s attribute found' % section_config.attribute_type
        return False
    elif len(att_list) > 1:
        print 'WARNING - Found %d attributes of type %s - using first one' % \
            (len(att_list), section_config.attribute_type)
    att = att_list[0]

    # If not in categories, don't write the section
    if not att.isMemberOf(categories):
        return True

    parent = att

    if section_config.group_name is not None:
        group = att.find(section_config.group_name)
        if group is None:
            print 'WARNING - NO %s group item found' % section_config.group_name
            return False
        parent = smtk.attribute.to_concrete(group)

    out.write('\n')

    if section_config.comment is not None:
        out.write('  # %s\n' % section_config.comment)

    format_string = '  %s %s\n'
    if section_config.title is not None:
        out.write('  %s\n' % section_config.title)
        format_string = '  ' + format_string

    # Special logic for material models (id)
    if att.type() == 'Material':
        global materialCounter
        matstring = '    id ' + str(materialCounter) + '\n'
        att.materialId = materialCounter
        materialCounter = materialCounter + 1
        out.write(matstring)

    format_list = format_table.get(section_config.attribute_type)
    if format_list is None:
        print 'WARNING: empty format list for %s' % att.type()
        return False

    for item_config in format_list:
        item = parent.find(item_config.name)

        if item is None:
            print 'WARNING: No %s item found' % item_config.name
            continue

        # Check item categories
        if not att.isMemberOf(categories):
            continue

        write_item_tree(item, item_config, format_string, out)

    if section_config.title is not None:
        out.write('  end\n')

     # Special logic for materialset -- hacked by acbauer
    if att.type() == 'Material':
        global materialSetCounter
        out.write('\n  materialset\n')
        out.write('    id %i\n' % materialSetCounter)
        materialSetCounter = materialSetCounter+1
        out.write('    material %i\n' % att.materialId)
        entities = att.associatedEntitiesSet()
        for entity in entities:
            out.write('    block %s\n' % get_id_from_name(entity.name()))
        out.write('  end\n')

    return True


def get_item_value(item, index=0):
    '''
    Returns Item value, handling VoidItem as a special case
    '''
    value = None
    if item.type() == smtk.attribute.Item.VOID:
        value = 'on' if item.isEnabled() else 'off'
    else:
        value = item.value(index)
    return value


def find_item(manager, attribute_type, *item_names):
    '''
    Finds and returns Item specified by attribute and list (path) of ancestor Items
    '''
    # Get attribute
    att_list = manager.findAttributes(attribute_type)
    if not att_list:
        return None
    elif len(att_list) > 1:
        print 'WARNING - Found %d attributes of type %s - using first one' % \
            (len(att_list), attribute_type)
    att = att_list[0]
    parent = att

    # Get items in sequence
    for item_name in item_names:
        item = parent.find(item_name)
        if item is None:
            return None
        parent = smtk.attribute.to_concrete(item)
        # Need to check that parent is Group?

    return parent


def find_item_config(attribute_type, *item_names):
    '''
    Finds and returns Item config from format_table
    '''
    #print 'attribute_type', attribute_type, 'item_names', item_names

    config_list = format_table.get(attribute_type)
    if config_list is None:
        return None

    #print 'config_list', config_list

    # Traverse item_names in sequence
    matching_config = None
    for item_name in item_names:
        # Traverse format_table[] for current item_name
        matching_config = None
        for config in config_list:
            #print 'config', config.__dict__
            if config.name == item_name:
                matching_config = config
                break

        if matching_config is None:
            return None
        elif hasattr(matching_config, 'item_format_list'):
            config_list = matching_config.item_format_list

    return matching_config


def get_domain_sets(model):
    '''Returns frozenset of model items that are domain sets.

    Current logic presumes that all groups with regions are domain sets.
    This function uses smtk.model.GroupItem.CastTo(), which is not
    implemented on smtk:master as of August 2014. If this method is not
    available, this function returns None for its output.
    '''
    # Confirm that smtk can generate the set
    if not hasattr(smtk.model.GroupItem, 'CastTo'):
        return None

    domain_sets = set()
    item_map = model.itemMap()
    #print 'item_map', item_map
    for model_item in item_map.values():
        #print 'model_item', model_item.type(), model_item.name()
        if (smtk.model.Item.Type.GROUP == model_item.type()):
            model_group_item = smtk.model.GroupItem.CastTo(model_item)
            #print 'model_group_item %s 0x%x' % (model_group_item.name(), model_group_item.entityMask())
            mask = model_group_item.entityMask()
            volume_mask = 0x8
            if volume_mask == (mask & volume_mask):
                #print 'domainset: %s %d' % (model_group_item.name(), model_group_item.id())
                domain_sets.add(model_item)
    return frozenset(domain_sets)


def get_loadcurve_id(item):
    '''Returns load curve id for smtk.attribute.DoubleItem

    Uses global lcid_dictionary.
    '''
    if item is None or not item.isEnabled():
        return None

    double_item = smtk.attribute.to_concrete(item)
    if double_item is None:
        print 'ERROR: %s item is not DoubleItem' % item.name()
        return None

    if not double_item.isExpression(0):
        print 'WARNING: %s item is not expression' % item.name()
        return None
    if not double_item.isSet(0):
        return None

    exp_ref = double_item.expressionReference(0)
    exp_att = exp_ref.value(0)
    name = exp_att.name()

    global lcid_dictionary
    lcid = lcid_dictionary.get(name)
    if lcid is None:
        lcid = len(lcid_dictionary) + 1
        print 'Assign lcid %d to function \"%s\"' % (lcid, name)
        lcid_dictionary[name] = lcid
    return lcid


def write_load_curves(manager, out):
    '''Writes all load curves in the lcid_dictionary.

    '''
    # Sort by id (dictionary value)
    lc_tuples = sorted(lcid_dictionary.items(), key=lambda t:t[1])
    for name, lcid in lc_tuples:
        att = manager.findAttribute(name)
        out.write('\n')
        out.write('  # Load Curve \"%s\"\n' % name)
        out.write('  load_curve\n')
        out.write('    id %s\n' % lcid)

        val_pairs_item = att.find('ValuePairs')
        val_pairs_group = smtk.attribute.GroupItem.CastTo(val_pairs_item)
        x_item = val_pairs_group.find('X')
        x_item = smtk.attribute.DoubleItem.CastTo(x_item)
        val_item = val_pairs_group.find('Value')
        val_item = smtk.attribute.DoubleItem.CastTo(val_item)
        num_vals = x_item.numberOfValues()
        for i in range(num_vals):
          out.write('      %.10e %.10e\n' % (x_item.value(i), val_item.value(i)))

        out.write('  end\n')
