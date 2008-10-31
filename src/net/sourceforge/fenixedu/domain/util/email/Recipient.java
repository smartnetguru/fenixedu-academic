package net.sourceforge.fenixedu.domain.util.email;

import java.util.Comparator;
import java.util.Set;

import net.sourceforge.fenixedu.domain.Person;
import net.sourceforge.fenixedu.domain.RootDomainObject;
import net.sourceforge.fenixedu.domain.accessControl.Group;
import pt.ist.fenixWebFramework.services.Service;

public class Recipient extends Recipient_Base {

    public static final Comparator<Recipient> COMPARATOR_BY_NAME = new Comparator<Recipient>() {

	@Override
	public int compare(Recipient r1, Recipient r2) {
	    final int c = r1.getToName().compareTo(r2.getToName());
	    return c == 0 ? COMPARATOR_BY_ID.compare(r1, r2) : c;
	}
	
    };

    public Recipient() {
	super();
	setRootDomainObject(RootDomainObject.getInstance());
    }

    public Recipient(final String toName, final Group members) {
	this();
	setRootDomainObject(RootDomainObject.getInstance());
	setToName(toName);
	setMembers(members);
    }

    @Service
    public void delete() {
	for (final Sender sender : getSendersSet()) {
	    removeSenders(sender);
	}
	removeRootDomainObject();
	deleteDomainObject();
    }

    public void addDestinationEmailAddresses(final Set<String> emailAddresses) {
	for (final Person person : getMembers().getElements()) {
	    final String emailAddress = person.getEmail();
	    if (emailAddress != null && !emailAddress.isEmpty()) {
		emailAddresses.add(emailAddress);
	    }
	}
    }

    @Override
    public String getToName() {
	if (super.getToName() == null) {
	    initToName();
	}
	return super.getToName();
    }

    @Service
    private void initToName() {
	setToName(getMembers().getName());
    }

    @Override
    public void setToName(final String toName) {
	super.setToName(toName);
    }

    @Service
    public static Recipient createNewRecipient(final String toName, final Group members) {
	return new Recipient(toName, members);
    }

}
