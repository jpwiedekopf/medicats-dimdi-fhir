package de.gecko.medicats.ops.ver_2007;

import de.gecko.medicats.FileSource;
import de.gecko.medicats.ZipSource;
import de.gecko.medicats.ops.OpsNodeFactory;
import de.gecko.medicats.ops.OpsNodeWalker;
import de.gecko.medicats.ops.sgml.AbstractSgmlOpsNodeFactory;

public class Ops2007NodeFactory extends AbstractSgmlOpsNodeFactory implements OpsNodeFactory
{
	private final ZipSource zip = new ZipSource(ZipSource.getBasePath(), "ops2007.zip", 3330600499L);

	private final FileSource sgml = new FileSource(zip, "ops2007erw", "p1ees2007", "Klassifikationsdateien",
			"OP301.SGM");
	private FileSource transitionFile = new FileSource(zip, "ops2007erw", "p1ueberw2006_2007", "Klassifikationsdateien",
			"UmsteigerErweitert.txt");
	private FileSource systFile = new FileSource(zip, "ops2007erw", "p1ueberw2006_2007", "Klassifikationsdateien",
			"opserw2007.txt");

	@Override
	public String getName()
	{
		return "OPS 2007";
	}

	@Override
	public String getOid()
	{
		return "1.2.276.0.76.5.317";
	}

	@Override
	public String getPreviousVersion()
	{
		return "ops2006";
	}

	@Override
	public String getVersion()
	{
		return "ops2007";
	}

	@Override
	public int getSortIndex()
	{
		return 2007;
	}

	@Override
	protected FileSource getSgml()
	{
		return sgml;
	}

	@Override
	protected FileSource getTransitionFile()
	{
		return transitionFile;
	}

	@Override
	protected FileSource getSystFile()
	{
		return systFile;
	}

	@Override
	public OpsNodeWalker createNodeWalker()
	{
		return new Ops2007NodeWalker(getRootNode());
	}

	@Override
	protected int getCurrentCodesColumn()
	{
		return 3;
	}

	@Override
	protected int getPreviousCodesForwardsCompatibleColumn()
	{
		return 6;
	}

	@Override
	protected int getCurrentCodesBackwardsCompatibleColumn()
	{
		return Integer.MIN_VALUE;
	}
}
