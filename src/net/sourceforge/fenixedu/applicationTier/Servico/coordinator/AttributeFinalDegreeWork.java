package net.sourceforge.fenixedu.applicationTier.Servico.coordinator;

import net.sourceforge.fenixedu.applicationTier.FenixService;
import net.sourceforge.fenixedu.domain.finalDegreeWork.FinalDegreeWorkGroup;
import net.sourceforge.fenixedu.domain.finalDegreeWork.GroupProposal;
import net.sourceforge.fenixedu.domain.finalDegreeWork.Proposal;
import pt.ist.fenixWebFramework.services.Service;

public class AttributeFinalDegreeWork extends FenixService {

    @Service
    public static void run(GroupProposal groupProposal) {
	if (groupProposal != null) {
	    Proposal proposal = groupProposal.getFinalDegreeWorkProposal();
	    proposal.setGroupAttributed(groupProposal.getFinalDegreeDegreeWorkGroup());

	    FinalDegreeWorkGroup group = groupProposal.getFinalDegreeDegreeWorkGroup();
	    for (GroupProposal otherGroupProposal : group.getGroupProposals()) {
		if (!(otherGroupProposal == groupProposal)
			&& otherGroupProposal.getFinalDegreeWorkProposal().getGroupAttributed() != null
			&& (otherGroupProposal.getFinalDegreeWorkProposal().getGroupAttributed() == group)) {
		    Proposal otherProposal = otherGroupProposal.getFinalDegreeWorkProposal();
		    otherProposal.removeGroupAttributed();
		}
	    }
	}
    }

}