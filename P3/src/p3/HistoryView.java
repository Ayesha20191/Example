package p3;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Text;
//import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

//import rcp_application.NavigationView.ViewContentProvider;
//import rcp_application.NavigationView.ViewLabelProvider;

//import rcp_application.NavigationView.TreeObject;
//import rcp_application.NavigationView.TreeParent;

public class HistoryView extends ViewPart {

	public HistoryView() {
		// TODO Auto-generated constructor stub
	}
	private TreeViewer viewer;

	class TreeObject {
		private String name;
		private TreeParent parent;

		public TreeObject(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
		public void setParent(TreeParent parent) {
			this.parent = parent;
		}
		public TreeParent getParent() {
			return parent;
		}
		public String toString() {
			return getName();
		}
	}

	class TreeParent extends TreeObject {
		private List<TreeObject> children;
		public TreeParent(String name) {
			super(name);
			children = new ArrayList<>();
		}
		public void addChild(TreeObject child) {
			children.add(child);
			child.setParent(this);
		}
		public void removeChild(TreeObject child) {
			children.remove(child);
			child.setParent(null);
		}
		public TreeObject[] getChildren() {
			return (TreeObject[]) children.toArray(new TreeObject[children.size()]);
		}
		public boolean hasChildren() {
			return children.size()>0;
		}
	}

	class ViewContentProvider implements IStructuredContentProvider, ITreeContentProvider {

		@Override
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		@Override
		public void dispose() {
		}

		@Override
		public Object[] getElements(Object parent) {
			return getChildren(parent);
		}

		@Override
		public Object getParent(Object child) {
			if (child instanceof TreeObject) {
				return ((TreeObject)child).getParent();
			}
			return null;
		}

		@Override
		public Object[] getChildren(Object parent) {
			if (parent instanceof TreeParent) {
				return ((TreeParent)parent).getChildren();
			}
			return new Object[0];
		}

		@Override
		public boolean hasChildren(Object parent) {
			if (parent instanceof TreeParent)
				return ((TreeParent)parent).hasChildren();
			return false;
		}
	}

	class ViewLabelProvider extends LabelProvider {

		@Override
		public String getText(Object obj) {
			return obj.toString();
		}

		@Override
		public Image getImage(Object obj) {
			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
			if (obj instanceof TreeParent)
				imageKey = ISharedImages.IMG_OBJ_FOLDER;
			return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
		}
	}

	/**
	 * We will set up a dummy model to initialize tree heararchy. In real
	 * code, you will connect to a real model and expose its hierarchy.
	 */
	private TreeObject createDummyModel() {
		TreeObject to1 = new TreeObject("Changes made in Task View");
		TreeObject to2 = new TreeObject("Edit the title");
		TreeObject to3 = new TreeObject("Modified the content of Item View");
		TreeParent p1 = new TreeParent("Todays History");
		p1.addChild(to1);
		p1.addChild(to2);
		p1.addChild(to3);

		TreeObject to4 = new TreeObject("Change in Editor Section");
		TreeParent p2 = new TreeParent("Yesterdays History");
		p2.addChild(to4);

		TreeParent root = new TreeParent("");
		root.addChild(p1);
		root.addChild(p2);
		return root;
	}
	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setInput(createDummyModel());
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		viewer.getControl().setFocus();
	}

}
